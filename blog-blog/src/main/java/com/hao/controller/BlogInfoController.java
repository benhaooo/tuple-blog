package com.hao.controller;

import com.hao.domain.ResponseResult;
import com.hao.service.BlogInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blogInfo")
public class BlogInfoController {

    @Autowired
    private BlogInfoService blogInfoService;

    @GetMapping("/")
    public ResponseResult getBlogInfo(){
        return blogInfoService.getBlogInfo();
    }
}
