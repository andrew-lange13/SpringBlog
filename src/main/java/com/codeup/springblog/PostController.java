package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {
    @GetMapping("/posts")
    @ResponseBody
    public String posts(){
        return "This is the posts index page";
    }
}
