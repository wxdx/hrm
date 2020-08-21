package me.wangxiaodong.hrm.module.user.dao;

import me.wangxiaodong.hrm.module.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<User,String> {

    @Query(value = "select * from hrm_user where login_name = ?1",nativeQuery = true)
    User findByLoginName(@Param("loginName") String loginName);

    @Query(value = "select count(*) from hrm_user where login_name = ?1",nativeQuery = true)
    int checkUser(@Param("username") String username);

    @Query(value = "select count(*) from hrm_user where login_name = ?1 and password = ?2",nativeQuery = true)
    int checkPassword(@Param("username") String username, @Param("password") String password);
}
