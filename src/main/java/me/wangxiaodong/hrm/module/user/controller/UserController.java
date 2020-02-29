package me.wangxiaodong.hrm.module.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.wangxiaodong.hrm.base.RespEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Api(description = "用户相关接口")
public class UserController {

    @ApiOperation(value = "新建用户",notes = "新建用户",httpMethod = "POST",response = RespEntity.class)
    @PostMapping(value = "/user")
    public RespEntity addUser(String name) {

        return RespEntity.success(name);
    }

    @ApiOperation(value = "删除用户",notes = "删除用户",httpMethod = "DELETE",response = RespEntity.class)
    @DeleteMapping(value = "/user")
    public RespEntity deleteUser(@RequestParam("userId") String userId) {

        return RespEntity.success(userId);
    }
}