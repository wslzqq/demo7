package com.example.demo7.controller;


import com.example.demo7.dao.User;
import com.example.demo7.dao.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.ValidationEvent;
import java.awt.*;

@RestController
@Api(value = "/",description = "这是我所有的sql接口")
public class SqlInterface {

    //获取一个执行sql语句的对象
    @Autowired
     private SqlSessionTemplate template;

    @RequestMapping(value = "/getUserCount",method = RequestMethod.GET)
    @ApiOperation(value = "查询接口",httpMethod = "GET")
    public  int getUserList(){

        return template.selectOne("getUserCount");

    }

    //添加用户接口
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ApiOperation(value = "添加用户接口" ,httpMethod = "POST")
    public int insertUser(@RequestBody UserInfo userInfo){
       return template.insert("addUser",userInfo);

    }

    //更新用户信息接口
    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    @ApiOperation(value = "用户信息更新接口",httpMethod = "POST")
    public int updateUser(@RequestBody UserInfo userInfo){
        return template.update("updateUser",userInfo);
    }
    //删除用户某个信息
    @ApiOperation(value = "删除用户信息接口",httpMethod = "POST")
    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    public int deletUpdate(@RequestBody UserInfo userInfo){
        return template.delete("deleteUser",userInfo);
    }

}
