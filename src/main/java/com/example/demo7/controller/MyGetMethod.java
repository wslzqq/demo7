package com.example.demo7.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "这是我全部的get请求")
public class MyGetMethod {
    @RequestMapping(value = "/getCookies",method = RequestMethod.GET)
    @ApiOperation(value = "通过这个方法可以获取到cookies",httpMethod = "GET")
    public String getCookies(HttpServletResponse response){
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        return "恭喜你获得cookies哈哈";
    }
    /**
     * 要求客户端携带cookies
     */
    @RequestMapping(value = "/getWithCookies",method = RequestMethod.GET)
    @ApiOperation(value = "要求客户端携带cookies",httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request){
        Cookie [] cookies = request.getCookies();
        if (Objects.isNull(cookies)){
            return "你必须携带cookies";
        }
        for (Cookie cookie:cookies){
            if (cookie.getName().equals("login")&&cookie.getValue().equals("true")){
                return "访问成功";

            }
        }
        return "你必须携带cookies";
    }
    /**
     * 开发一个需要携带参数才能访问的get请求
     *实现方式 url:key=value&key=value
     * 模拟获取商品列表
     */
    @RequestMapping(value = "/getWithParmeter",method = RequestMethod.GET)
    @ApiOperation(value = "需要携带参数才能访问的get请求",httpMethod = "GET")
    public Map<String,Integer> getList(@RequestParam Integer start, @RequestParam Integer end){
        Map<String,Integer> myList = new HashMap<>();
        myList.put("鞋",400);
        myList.put("干脆面",1);
        myList.put("衬衫",1);
        return myList;

    }
    /**
     * 第二种开发一个需要携带参数才能访问的get请求
     *实现方式 url:ip:port/get/with.parmeter
     * 模拟获取商品列表
     */

    @RequestMapping(value = "/get/with/param/{start}/{end}",method = RequestMethod.GET)
    @ApiOperation(value = "需要携带参数才能访问的get请求",httpMethod = "GET")
    public Map<String,Integer> myGetList(@RequestParam Integer start,@RequestParam Integer end){
        Map<String,Integer> myList = new HashMap<>();
        myList.put("鞋",300);
        myList.put("干脆面",2);
        myList.put("衬衫",300);
        return myList;

    }
}
