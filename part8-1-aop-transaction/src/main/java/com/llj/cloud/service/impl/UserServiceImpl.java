package com.llj.cloud.service.impl;

import com.llj.cloud.dao.UserDao;
import com.llj.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * PackageName:   com.llj.cloud.staticProxy
 * projectName:   architect-plan
 * Description:   luolinjie 补充
 * Creator:     by luolinjie
 * Create_Date: 2019/9/23 23:36
 * Updater:     by luolinjie
 * Update_Date: 2019/9/23
 * Update_Description: luolinjie 补充
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Transactional
    public void add() {
//        try {
            userDao.add("zhangsan", 22);
            int i = 1 / 0;
            userDao.add("lisi", 24);

//        } catch (Exception e) {
//            e.printStackTrace();

//            throw new RuntimeException();//如果使用catch，则需要手动抛出异常，让AOP事务类去捕获处理
//        }

    }

}
