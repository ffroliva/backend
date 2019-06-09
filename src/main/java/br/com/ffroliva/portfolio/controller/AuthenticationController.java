package br.com.ffroliva.portfolio.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {


    @RequestMapping("/home")
    public String hello() {
        return "Hello buddy!";
    }


}
