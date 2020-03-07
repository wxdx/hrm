package me.wangxiaodong.hrm.module.user.service.impl;

import me.wangxiaodong.hrm.module.user.dao.UserDao;
import me.wangxiaodong.hrm.module.user.entity.User;
import me.wangxiaodong.hrm.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User findByLoginName(String loginName) {
        return userDao.findByLoginName(loginName);
    }
}
