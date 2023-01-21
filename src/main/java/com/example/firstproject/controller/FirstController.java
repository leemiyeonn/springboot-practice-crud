package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi") // url요청 연결
    public String niceToMeetYou(Model model){
        model.addAttribute("username", "이스프링");
        return "greetings"; //templates/greetings.mustache -> 브라우저로 전송
    }

    @GetMapping("/bye")
    public String seeyounext(Model model){
        model.addAttribute("nickname", "김스프링");
        return "goodbye";
    }


}
