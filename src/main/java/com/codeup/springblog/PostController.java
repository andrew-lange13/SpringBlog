package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository postDao, UserRepository userDao){
        this.postDao = postDao;
        this.userDao = userDao;
    }

    private final UserRepository userDao;


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
    public String createPostGet(Model model){
        model.addAttribute("post", new Post());
        return "posts/create";
    }
    @PostMapping("/posts/create")
    public String createPost(@RequestParam(name = "title") String title, @RequestParam(name = "body") String body){
        postDao.save(new Post(title, body));
        return "redirect:/posts";
    }
}
