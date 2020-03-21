package me.wangxiaodong.hrm.filter;

import me.wangxiaodong.hrm.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor  extends HandlerInterceptorAdapter {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){


        //登录校验
        //1.获取token
        String token = request.getHeader("token");

        //如果没有token 直接返回401
        if (token == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        //判断token是否过期
        boolean flag = JwtUtil.checkExpireToken(token);
        if (flag){
            return true;
        } else {
            log.warn("token过期需要重新登录");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

    }

}