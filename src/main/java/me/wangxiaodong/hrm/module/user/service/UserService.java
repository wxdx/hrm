package me.wangxiaodong.hrm.module.user.service;

import me.wangxiaodong.hrm.module.user.entity.User;

public interface UserService {

    //根据登录用户名查询用户信息
    User findByLoginName(String loginName);

    //保存新用户
    void save(User user);

    int checkUser(String username);

    int checkPassword(String username, String password);
}
