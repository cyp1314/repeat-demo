package com.example.repeatdemo.controller;

import com.example.repeatdemo.annotation.JRepeat;
import com.example.repeatdemo.entity.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

    @JRepeat(lockKey = "#name", lockTime = 5000)
    @GetMapping("hello")
    public String hello(@RequestParam("name") String name){
        return "hello,"+ name;
    }

    @JRepeat(lockKey = "#student", lockTime = 5000)
    @PostMapping("save")
    public String save(@RequestBody Student student) throws JsonProcessingException {
        log.info("学生信息{}",student);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(student);
    }
}
