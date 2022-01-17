package com.example.repeatdemo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable {
    private long id;
    private String name;
    private Integer age;
    private String sex;
    private String address;
}
