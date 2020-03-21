package me.wangxiaodong.hrm.module.user.controller;

import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.wangxiaodong.hrm.base.RespEntity;
import me.wangxiaodong.hrm.module.user.entity.User;
import me.wangxiaodong.hrm.module.user.service.UserService;
import me.wangxiaodong.hrm.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@Api(description = "用户相关接口")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户信息",notes = "获取用户信息",httpMethod = "GET",response = RespEntity.class)
    @GetMapping(value = "/info")
    public RespEntity getUser(@RequestHeader String token) {
        Claims claims = JwtUtil.parseJWT(token);
        if (claims != null) {
            String username = claims.getSubject();
            User user = userService.findByLoginName(username);
            return RespEntity.success(user);
        }
        return RespEntity.fail(null);
    }

}