package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {
    @GetMapping("/posts")
    @ResponseBody
    public String posts(){
        return "This is the posts index page";
    }
    @GetMapping("/posts/{id}")
    @ResponseBody
    public String singlePost(@PathVariable int id){
        return "This is the number " + id + " post";
    }
    @GetMapping("/posts/create")
    @ResponseBody
    public String createPostGet(){
        return "This is the create a post get page";
    }
    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost(){
        return "This is the create a post post page";
    }
}
