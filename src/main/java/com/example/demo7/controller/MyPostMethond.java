package com.example.demo7.controller;


import com.example.demo7.dao.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/", description = "这是我全部的post请求")
public class MyPostMethond {

    //这个变量是用来装我们的cookies信息的
    private static Cookie cookie;

    //用户登录成功获取到cookies，然后再访问其他接口列表
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "登陆接口,成功后获取cookie信息", httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value = "username", required = true) String username,
                        @RequestParam(value = "password", required = false) String password) {

        if (username.equals("zhangsan") && password.equals("123456")) {
            cookie = new Cookie("login", "true");
            return "恭喜你登陆成功";
        }
        return "用户名或密码错误";
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表", httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                              @RequestBody User u) {
        User user;
        //获取cookies
        Cookie [] cookies = request.getCookies();

        //验证cookies 是否合法
        for (Cookie c : cookies) {
            if (c.getName().equals("login") && c.getValue().equals("true")
                    && u.getUsername().equals("zhangsan")
                    && u.getPassword().equals("123456")
                    ) {
                user = new User();
                user.setName("lisi");
                user.setAge("18");
                user.setSex("man");
                return user.toString();

            }
        }
            return "参数不合法 ";

    }
}
