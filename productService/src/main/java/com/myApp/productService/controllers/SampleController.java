package com.myApp.productService.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//this class will be hosting a set of HTTP API's
@RestController
@RequestMapping("/say")//address of this controller class
public class SampleController {

    @GetMapping("/hello/{name}/{time}")//address of this method
    public String sayHello(@PathVariable("name") String xyz, @PathVariable("time") int time){
        String output= "";
        for (int i=0;i<=time;i++){
            output=output+ "hey"+ xyz;
        }
        return output;
    }
    @GetMapping("/bye")
    public String sayBye(){
        return "bye everyone";
    }
}
