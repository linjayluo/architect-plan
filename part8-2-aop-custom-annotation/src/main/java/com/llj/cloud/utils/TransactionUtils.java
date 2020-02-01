package com.llj.cloud.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * PackageName:   com.llj.cloud.transaction
 * projectName:   architect-plan
 * Description:   luolinjie 补充
 * Creator:     by luolinjie
 * Create_Date: 2020/1/29 22:15
 * Updater:     by luolinjie
 * Update_Date: 2020/1/29
 * Update_Description: luolinjie 补充
 **/
@Component
@Scope("prototype") //使用多例模式获取该类，防止出现线程安全问题
public class TransactionUtils {
    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;

    private TransactionStatus status;

    //开启事务
    public TransactionStatus begin() {
        status = dataSourceTransactionManager.getTransaction(new DefaultTransactionDefinition());
        return status;
    }

    //提交事务
    public void commit(TransactionStatus status) {
        if (null != status) {
            dataSourceTransactionManager.commit(status);
        }
    }

    //回滚事务
    public void rollback() {
        dataSourceTransactionManager.rollback(status);
    }
}
