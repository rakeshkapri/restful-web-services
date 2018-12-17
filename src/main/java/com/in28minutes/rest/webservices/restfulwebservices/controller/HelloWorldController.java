package com.in28minutes.rest.webservices.restfulwebservices.controller;

import com.in28minutes.rest.webservices.restfulwebservices.entity.HelloWorldBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {
    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean", produces = MediaType.APPLICATION_XHTML_XML_VALUE)
    public HelloWorldBean helloWorldBean(){
        return HelloWorldBean.builder().message("Hello World Bean").build();
    }

    @GetMapping(path = "/hello-world-bean/path-variable/{name}")
    public String helloWorldBeanPathVariable(@PathVariable String name){
        return String.format("Hello World Bean %s", name);
    }

    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized(){
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }

}
