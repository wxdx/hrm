package me.wangxiaodong.hrm.module.user.service;

import me.wangxiaodong.hrm.module.user.entity.User;

public interface UserService {

    //根据登录用户名查询用户信息
    User findByLoginName(String loginName);
}
