package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {
    @GetMapping("/posts")
    public String posts(){

        return "posts/index";
    }
    @GetMapping("/posts/{id}")
    public String singlePost(@PathVariable int id, Model model){
        Post post = new Post("first", "i like turtles");
        model.addAttribute("createdPost", post);
        return "posts/show";
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
