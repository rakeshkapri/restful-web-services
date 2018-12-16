package com.in28minutes.rest.webservices.restfulwebservices.controller;

import com.in28minutes.rest.webservices.restfulwebservices.entity.HelloWorldBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return HelloWorldBean.builder().message("Hello World Bean").build();
    }

}
