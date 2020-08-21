package me.wangxiaodong.hrm.module.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.wangxiaodong.hrm.base.RespEntity;
import me.wangxiaodong.hrm.module.email.service.IMailService;
import me.wangxiaodong.hrm.module.user.entity.User;
import me.wangxiaodong.hrm.module.user.service.UserService;
import me.wangxiaodong.hrm.utils.JwtUtil;
import me.wangxiaodong.hrm.utils.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@RestController
@Api(description = "登录注册相关接口")
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private IMailService iMailService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @ApiOperation(value = "登录",notes = "登录",httpMethod = "POST",response = RespEntity.class)
    @PostMapping
    public RespEntity login(@RequestBody User user) {
        if (user == null) return RespEntity.fail(null);
        //先去判断是否存在此用户
        int i = userService.checkUser(user.getLoginName());
        if (i > 0){
            int j = userService.checkPassword(user.getLoginName(),user.getPassword());
            if (j > 0) {
                String token = JwtUtil.sign(user.getLoginName(), user.getId());
                return RespEntity.success(token);
            } else {
                RespEntity fail = RespEntity.fail(user.getLoginName());
                fail.setMsg("密码不正确！");
                return fail;
            }
        } else {
            RespEntity fail = RespEntity.fail(user.getLoginName());
            fail.setMsg("此用户不存在！");
            return fail;
        }
    }

    @ApiOperation(value = "检查token是否失效",notes = "检查token是否失效",httpMethod = "GET",response = RespEntity.class)
    @GetMapping(value = "/checkToken")
    public RespEntity checkToken(@RequestHeader String token) {
        return RespEntity.success(JwtUtil.checkExpireToken(token));
    }

    @ApiOperation(value = "注册用户",notes = "注册用户",httpMethod = "POST",response = RespEntity.class)
    @PostMapping(value = "/register",produces = {"application/json"})
    public RespEntity registerUser(@RequestBody @Valid User user) {
        if (null != user){
            SnowFlake snowFlake = new SnowFlake(0,0);
            user.setId(snowFlake.nextId());
            user.setRegisterTime(new Date());
            user.setStatus("1");
            userService.save(user);
        } else {
            return RespEntity.fail(null);
        }
        return RespEntity.success(user.getLoginName());
    }


    @ApiOperation(value = "判断用户名是否存在",notes = "判断用户名是否存在",httpMethod = "GET",response = RespEntity.class)
    @GetMapping(value = "/register/exist/{loginName}")
    public RespEntity userExist(@PathVariable String loginName) {
        //判断用户是否存在
        User userFlag = userService.findByLoginName(loginName);
        if (userFlag != null){
            return RespEntity.fail(loginName);
        }
        return RespEntity.success(loginName);
    }

    @ApiOperation(value = "生成emailCode",notes = "生成emailCode",httpMethod = "GET",response = RespEntity.class)
    @GetMapping(value = "/register/sendEmailCode")
    public RespEntity emailCode(@RequestParam String email) {
        int randomNumber = (int)((Math.random()*9+1)*1000);
        redisTemplate.opsForValue().set(email,String.valueOf(randomNumber), 5, TimeUnit.MINUTES);
        iMailService.sendSimpleMail(email,"人力资源系统注册验证邮件","验证码：" + randomNumber);
        return RespEntity.success(email);
    }

    @ApiOperation(value = "检查emailCode",notes = "检查emailCode",httpMethod = "GET",response = RespEntity.class)
    @GetMapping(value = "/register/checkEmailCode")
    public RespEntity checkEmailCode(@RequestParam String email,@RequestParam String emailCode) {
        //读取存在redis中的emailCode是否正确。
        String redisString = (String) redisTemplate.opsForValue().get(email);
        if (emailCode.equals(redisString)){
            return RespEntity.success(email);
        } else {
            return RespEntity.fail(email);
        }
    }





}
