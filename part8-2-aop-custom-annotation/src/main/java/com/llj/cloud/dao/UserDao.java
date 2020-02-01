package com.llj.cloud.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * PackageName:   dao
 * projectName:   architect-plan
 * Description:   luolinjie 补充
 * Creator:     by luolinjie
 * Create_Date: 2020/1/29 22:30
 * Updater:     by luolinjie
 * Update_Date: 2020/1/29
 * Update_Description: luolinjie 补充
 **/
@Repository
public class UserDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void add(String name, Integer age) {
        String sql = "insert into user(name,age) values (?,?)";
        int update = jdbcTemplate.update(sql,name,age);
        System.out.println(update + " records updated");
    }
}
