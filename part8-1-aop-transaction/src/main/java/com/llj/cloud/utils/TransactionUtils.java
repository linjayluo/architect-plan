package com.llj.cloud.utils;

import org.springframework.beans.factory.annotation.Autowired;
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
public class TransactionUtils {
    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;

    //开启事务
    public TransactionStatus begin() {
        TransactionStatus transaction = dataSourceTransactionManager.getTransaction(new DefaultTransactionDefinition());
        return transaction;
    }

    //提交事务
    public void commit(TransactionStatus status) {
        dataSourceTransactionManager.commit(status);
    }

    //回滚事务
    public void roolback(TransactionStatus status) {
        dataSourceTransactionManager.rollback(status);
    }
}
