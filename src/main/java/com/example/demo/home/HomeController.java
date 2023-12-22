package com.example.demo.home;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping("/hello")
    @ResponseBody
    public String index() {
        return "안녕하세요";
    }
    @GetMapping("/name")
    @ResponseBody
    public String index2() {
        return "저는 김두환입니다.";
    }
    @GetMapping("/age")
    @ResponseBody
    public String index3() {
        return "저는 30살 입니다.";
    }
}