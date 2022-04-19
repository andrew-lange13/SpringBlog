package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository postDao){
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    public String posts(Model model){
//        ArrayList<Post> postsIndex = new ArrayList<>();
//        postsIndex.add(new Post("first", "i like turtles"));
//        postsIndex.add(new Post("second", "if you ain't first you're last"));
//        model.addAttribute("createdIndex", postsIndex);
        model.addAttribute("createdIndex", postDao.findAll());
        return "posts/index";
    }
    @GetMapping("/posts/{id}")
    public String singlePost(@PathVariable int id, Model model){
        model.addAttribute("createdPost", postDao.getById(id));
//        Post post = new Post("first", "i like turtles");
//        model.addAttribute("createdPost", post);
        return "posts/show";
    }
    @GetMapping("/posts/create")
    public String createPostGet(){
        return "posts/create";
    }
    @PostMapping("/posts/create")
    public String createPost(Model model){
        String title = (String) model.getAttribute("title");
        String body = (String) model.getAttribute("body");
        Post post = new Post(title, body);
        model.addAttribute("post", post);
        return "posts/create";
    }
}
