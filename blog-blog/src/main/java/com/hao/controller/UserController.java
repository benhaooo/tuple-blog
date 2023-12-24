package com.hao.controller;

import com.hao.domain.ResponseResult;
import com.hao.domain.entity.UserInfo;
import com.hao.domain.vo.UserInfoVo;
import com.hao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }


    @PostMapping("/update")
    public  ResponseResult updateUserInfo(@RequestBody UserInfoVo userInfoVo){
     return userService.updateUserInfo(userInfoVo);
    }

    @PostMapping("/update/ava")
    public ResponseResult updateUserAva(MultipartFile img){
        return userService.updateUserAva(img);
    }
    @GetMapping("/del")
    public  ResponseResult delUser(String id){
        return userService.delUser(id);
    }


    @GetMapping("/info/detail")
    public ResponseResult userInfoDetail(){
        return userService.userInfoById();
    }

    @PutMapping("/info/detail")
    public ResponseResult updateUserInfoDetail(@RequestBody UserInfo userInfo){
        return userService.updateUserInfoById(userInfo);
    }

    @GetMapping("/mylike")
    public ResponseResult getMyLikeArticles(){
        return userService.getMyLikeArticles();
    }


}