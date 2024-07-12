package com.github.xiaoyu2017.bbs.controller;

import com.github.xiaoyu2017.bbs.pojo.bean.Result;
import com.github.xiaoyu2017.bbs.pojo.bean.ResultCode;
import com.github.xiaoyu2017.bbs.pojo.dto.AuthDto;
import com.github.xiaoyu2017.bbs.pojo.dto.RegisterDto;
import com.github.xiaoyu2017.bbs.pojo.vo.LoginVo;
import com.github.xiaoyu2017.bbs.pojo.vo.RegisterVo;
import com.github.xiaoyu2017.bbs.server.AuthService;
import com.github.xiaoyu2017.bbs.tool.BeanTool;
import com.github.xiaoyu2017.bbs.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 授权管理
 *
 * @author xiaoyu
 * @version 1.0
 */

@RestController
@Api(tags = "授权管理")
public class AuthController {

    final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * 用户注册
     *
     * @param registerVo 注册内容
     * @return ResponseEntity
     */
    @PostMapping("/register")
    @ApiOperation(value = "用户注册", notes = "用户注册")
    public ResponseEntity<Result<String>> register(RegisterVo registerVo) {
        if (Objects.isNull(registerVo)) {
            return ResponseEntity.ok(Result.error(ResultCode.REGISTER_ERROR_400_1));
        }
        if (!StrUtil.userNameVerify(registerVo.getUserName())) {
            return ResponseEntity.ok(Result.error(ResultCode.REGISTER_ERROR_400_2));
        }
        if (!StrUtil.passwordVerify(registerVo.getPassword())) {
            return ResponseEntity.ok(Result.error(ResultCode.REGISTER_ERROR_400_3));
        }
        boolean register = authService.register(BeanTool.toTargetBean(registerVo, RegisterDto.class));
        return register ? ResponseEntity.ok(Result.success(ResultCode.REGISTER_SUCCESS_200)) : ResponseEntity.ok(Result.error(ResultCode.REGISTER_ERROR_500));
    }

    /**
     * 用户登录
     *
     * @param response 响应对象
     * @param loginVo  登录界面模型对象
     * @return ResponseEntity
     */
    @PostMapping("/login")
    @ApiOperation(value = "用户登录", notes = "用户登录")
    public ResponseEntity<Result<String>> login(HttpServletResponse response, LoginVo loginVo) {
        if (Objects.isNull(loginVo)) {
            return ResponseEntity.ok(Result.error(ResultCode.LOGIN_ERROR_400_1));
        }
        if (StringUtils.isBlank(loginVo.getUserName()) || StringUtils.isBlank(loginVo.getPassword())) {
            return ResponseEntity.ok(Result.error(ResultCode.LOGIN_ERROR_400_2));
        }
        String token;
        try {
            token = authService.verify(BeanTool.toTargetBean(loginVo, AuthDto.class));
        } catch (Exception e) {
            return ResponseEntity.ok(Result.error(ResultCode.LOGIN_ERROR_500, e));
        }
        if (StringUtils.isBlank(token)) {
            return ResponseEntity.ok(Result.error(ResultCode.LOGIN_ERROR_500));
        }
        Cookie cookie = new Cookie(StrUtil.USER_TOKEN, token);
        response.addCookie(cookie);
        return ResponseEntity.ok(Result.success(ResultCode.LOGIN_SUCCESS_200));
    }

    /**
     * 用户退出登录
     *
     * @param response 请求响应
     * @return ResponseEntity
     */
    @GetMapping("/logout")
    @ApiOperation(value = "退出登录", notes = "退出登录")
    public ResponseEntity<Result<String>> logout(HttpServletResponse response) {
        if (authService.logout(response)) {
            return ResponseEntity.ok(Result.success(ResultCode.LOGOUT_SUCCESS_200));
        }
        return ResponseEntity.ok(Result.error(ResultCode.LOGOUT_ERROR_400));
    }

    @GetMapping("/test")
    public String test() {
        return "测试接口";
    }
}
