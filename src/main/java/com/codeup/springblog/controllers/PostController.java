package com.codeup.springblog.controllers;

import com.codeup.springblog.services.EmailService;
import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;
    private final EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService){
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
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
    public String singlePost(@PathVariable long id, Model model){
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
    public String createPost(Post post){
        post.setOwner(userDao.getUserById(1));
        emailService.prepareAndSend(post, "SpringBlog", "You created a post!");
        postDao.save(post);
        return "redirect:/posts";
    }
    @GetMapping("/posts/{id}/edit")
    public String getPost(@PathVariable long id, Model model){
        model.addAttribute("createdPost", postDao.getPostById(id));
        return "/posts/edit";
    }

    @PostMapping("/posts/edit")
    public String editPost(@ModelAttribute Post post){
        postDao.save(post);
        return "redirect:/posts";
    }
}
