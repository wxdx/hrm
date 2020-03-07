package me.wangxiaodong.hrm.module.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "用户实体")
public class User {
    //主键ID
    @ApiModelProperty(value = "主键ID")
    private String id;
    //用户ID
    @ApiModelProperty(value = "用户ID")
    private String userId;
    //用户登录名
    @ApiModelProperty(value = "用户登录名")
    private String loginName;
    //用户昵称
    @ApiModelProperty(value = "用户昵称")
    private String nickName;
    //登录密码
    @ApiModelProperty(value = "登录密码")
    private String password;
    //邮箱地址
    @ApiModelProperty(value = "邮箱地址")
    private String email;
    //注册时间
    @ApiModelProperty(value = "注册时间")
    private Date registerTime;
    //修改时间
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
    //用户状态 0 已删除 1 正常
    @ApiModelProperty(value = "用户状态")
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
