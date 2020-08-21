package me.wangxiaodong.hrm.module.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@ApiModel(value = "用户实体")
@Data
@Entity
@Table(name = "hrm_user")
public class User {
    //主键ID
    @ApiModelProperty(value = "主键ID")
    @Id
    private String id;
    //用户ID
    @ApiModelProperty(value = "用户ID")
    private String userId;
    //用户登录名
    @ApiModelProperty(value = "用户登录名")
    @NotNull(message = "登陆用户名不能为空")
    @Size(max = 16)
    private String loginName;
    //用户昵称
    @ApiModelProperty(value = "用户昵称")
    @NotNull(message = "用户昵称不能为空")
    @Size(max = 16)
    private String nickName;
    //登录密码
    @ApiModelProperty(value = "登录密码")
    @NotNull(message = "密码不能为空")
    @Size(max = 36)
    private String password;
    //邮箱地址
    @ApiModelProperty(value = "邮箱地址")
    @Email(message = "email 格式不正确")
    @NotNull(message = "email 不能为空")
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
}
