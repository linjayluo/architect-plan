package com.llj.cloud.annotation;

import com.llj.cloud.utils.TransactionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

import java.lang.reflect.Method;

/**
 * PackageName:   aop
 * projectName:   architect-plan
 * Description:   luolinjie 补充
 * Creator:     by luolinjie
 * Create_Date: 2020/1/29 23:05
 * Updater:     by luolinjie
 * Update_Date: 2020/1/29
 * Update_Description: luolinjie 补充
 **/
@Component
@Aspect
public class AopExtTransaction {
    @Autowired
    TransactionUtils transactionUtils;

    /**
     * 抛出异常后
     */
    @AfterThrowing("execution(* com.llj.cloud.service.*.*.*(..))")
    public void afterThrowing() {
        //获取当前事务直接回滚
//        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        transactionUtils.rollback();
    }

    @Around("execution(* com.llj.cloud.service.*.*.*(..))")
    public void around(ProceedingJoinPoint pjp) throws Throwable {

        //调用方法之前执行
        TransactionStatus transactionStatus = begin(pjp);
        pjp.proceed();
        //调用方法之后执行
        commit(transactionStatus);
    }

    private void commit(TransactionStatus transactionStatus) {
        if (null != transactionStatus) {
            transactionUtils.commit(transactionStatus);
        }
    }

    private TransactionStatus begin(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        ExtAnnotation extAnnotation = getExtAnnotation(pjp);
        if (null == extAnnotation) {
            return null;
        }
        //3：如果存在该事务注解，则开启事务
        TransactionStatus transactionStatus = null;
        if (null != extAnnotation) {
            transactionStatus = transactionUtils.begin();
        }
        return transactionStatus;
    }

    private ExtAnnotation getExtAnnotation(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        //1：获取代理对象的方法
        //获取方法名称
        String methodName = pjp.getSignature().getName();
        //获取目标对象
        Class<?> classTarget = pjp.getTarget().getClass();
        //获取目标对象类型
        Class[] parameterTypes = ((MethodSignature) pjp.getSignature()).getParameterTypes();
        //获取目标对象方法
        Method method = classTarget.getMethod(methodName, parameterTypes);

        //2：获取方法上的注解
        ExtAnnotation extAnnotation = method.getDeclaredAnnotation(ExtAnnotation.class);
        if (null == extAnnotation) {
            System.out.println("方法 " + methodName + " 上没有加 @ExtAnnotation 注解");
            return null;
        }

        return extAnnotation;
    }


}
