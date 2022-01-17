package com.example.repeatdemo.controller;

import com.example.repeatdemo.annotation.JRepeat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @JRepeat(lockKey = "#name", lockTime = 5000)
    @GetMapping("hello")
    public String hello(@RequestParam("name") String name){
        return "hello,"+ name;
    }
}
