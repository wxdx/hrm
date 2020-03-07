package me.wangxiaodong.hrm.module.user.dao;

import me.wangxiaodong.hrm.module.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {

    @Select("select * from user where loginName = #{0}")
    User findByLoginName(String loginName);
}
