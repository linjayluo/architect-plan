package com.llj.cloud.aop;

import com.llj.cloud.utils.TransactionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
//@Component
@Aspect
public class AopTransaction {
    @Autowired
    TransactionUtils transactionUtils;

    /**
     * 抛出异常后
     */
    @AfterThrowing("execution(* com.llj.cloud.service.UserService.add(..))")
    public void afterThrowing() {
        System.out.println("回滚事务");
        //获取当前事务直接回滚
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }

    @Around("execution(* com.llj.cloud.service.UserService.add(..))")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("开启事务");

        TransactionStatus transactionStatus = transactionUtils.begin();

        joinPoint.proceed();

        System.out.println("提交事务");
        transactionUtils.commit(transactionStatus);
    }
}
