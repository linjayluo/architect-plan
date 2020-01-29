package com.llj.cloud.staticProxy;
import com.llj.cloud.UserService;
import com.llj.cloud.UserServiceImpl;

/**
 * PackageName:   com.llj.cloud.staticProxy
 * projectName:   architect-plan
 * Description:   luolinjie 补充
 * Creator:     by luolinjie
 * Create_Date: 2019/9/23 23:37
 * Updater:     by luolinjie
 * Update_Date: 2019/9/23
 * Update_Description: luolinjie 补充
 **/
public class UserImplProxy implements UserService {
    private UserServiceImpl userServiceImpl;

    public UserImplProxy(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public void add() {
        System.out.println("before user add()");
        userServiceImpl.add();
        System.out.println("after user add()");
    }
}
