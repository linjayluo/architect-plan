package com.llj.cloud.staticProxy;
import com.llj.cloud.UserService;
import com.llj.cloud.UserServiceImpl;

/**
 * PackageName:   com.llj.cloud.staticProxy
 * projectName:   architect-plan
 * Description:   luolinjie 补充
 * Creator:     by luolinjie
 * Create_Date: 2019/9/23 23:40
 * Updater:     by luolinjie
 * Update_Date: 2019/9/23
 * Update_Description: luolinjie 补充
 **/
public class mainEntry {

    public static void main(String[] args) {
        UserServiceImpl userServiceImpl = new UserServiceImpl();

        UserService proxy = new UserImplProxy(userServiceImpl);

        proxy.add();

    }
}
