package me.wangxiaodong.hrm.module.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.wangxiaodong.hrm.base.RespEntity;
import me.wangxiaodong.hrm.module.email.service.IMailService;
import me.wangxiaodong.hrm.module.user.entity.User;
import me.wangxiaodong.hrm.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@Api(description = "用户相关接口")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private IMailService iMailService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @ApiOperation(value = "新建用户",notes = "新建用户",httpMethod = "POST",response = RespEntity.class)
    @PostMapping(value = "/user")
    public RespEntity registerUser(@RequestBody(required = true) User user,@RequestParam String emailCode) {
        //读取存在redis中的emailCode是否正确。
        String redisString = (String) redisTemplate.opsForValue().get(user.getEmail());
        if (emailCode.equals(redisString)){
            user.setId(UUID.randomUUID().toString());
            user.setUserId(UUID.randomUUID().toString());
            user.setRegisterTime(new Date());
            user.setStatus("1");
            userService.save(user);
        } else {
            return RespEntity.fail(user.getLoginName());
        }
        return RespEntity.success(user.getLoginName());
    }


    @ApiOperation(value = "判断用户是否存在",notes = "判断用户是否存在",httpMethod = "GET",response = RespEntity.class)
    @GetMapping(value = "/user/exist")
    public RespEntity userExist(@RequestParam String loginName) {
        //判断用户是否存在
        User userFlag = userService.findByLoginName(loginName);
        if (userFlag != null){
            return RespEntity.fail(loginName);
        }
        return RespEntity.success(loginName);
    }

    @ApiOperation(value = "生成emailCode",notes = "生成emailCode",httpMethod = "GET",response = RespEntity.class)
    @GetMapping(value = "/user/emailCode")
    public RespEntity emailCode(@RequestParam String email) {
        int randomNumber = (int)((Math.random()*9+1)*1000);
        redisTemplate.opsForValue().set(email,String.valueOf(randomNumber), 5,TimeUnit.MINUTES);
        iMailService.sendSimpleMail(email,"人力资源系统注册验证邮件","验证码：" + randomNumber);
        return RespEntity.success(email);
    }

    @ApiOperation(value = "检查emailCode",notes = "检查emailCode",httpMethod = "GET",response = RespEntity.class)
    @GetMapping(value = "/user/checkEmailCode")
    public RespEntity checkEmailCode(@RequestParam String email,@RequestParam String emailCode) {
        //读取存在redis中的emailCode是否正确。
        String redisString = (String) redisTemplate.opsForValue().get(email);
        if (emailCode.equals(redisString)){
            return RespEntity.success(email);
        } else {
            return RespEntity.fail(email);
        }
    }

    @ApiOperation(value = "删除用户",notes = "删除用户",httpMethod = "DELETE",response = RespEntity.class)
    @DeleteMapping(value = "/user")
    public RespEntity deleteUser(@RequestParam("userId") String userId) {

        return RespEntity.success(userId);
    }

}