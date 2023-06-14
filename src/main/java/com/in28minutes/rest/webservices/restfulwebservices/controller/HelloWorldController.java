package com.in28minutes.rest.webservices.restfulwebservices.controller;

import com.in28minutes.rest.webservices.restfulwebservices.entity.HelloWorldBean;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {
    @GetMapping()
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping("/bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World!");
    }

    @GetMapping("/{name}")
    public HelloWorldBean helloWorldBeanPathVariable(
            @PathVariable String name){
        return new HelloWorldBean(
                String.format("Hello World, %s", name));
    }

    @GetMapping("/internationalized")
    public String helloWorldInternationalized(){
        return "Hello World V2";
    }
}
