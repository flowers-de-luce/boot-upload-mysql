package com.jiabao.uploadMySQL.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UploadHomeController {
    @RequestMapping("/file")
    public String file(){
        System.out.println("============请求路径======跳转file页面===============");
        return "/file";
    }
}
