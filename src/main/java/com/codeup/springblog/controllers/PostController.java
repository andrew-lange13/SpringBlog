package com.codeup.springblog.controllers;

import com.codeup.springblog.models.User;
import com.codeup.springblog.services.EmailService;
import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public String createPost(@Valid Post post, Errors validation, Model model){
        if (validation.hasErrors()){
            model.addAttribute("errors", validation);
            model.addAttribute("post", post);
            return "posts/create";
        }
        post.setOwner((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        emailService.prepareAndSend(post, "SpringBlog", "You created a post!");
        postDao.save(post);
        return "redirect:/posts";
    }
    @GetMapping("/posts/{id}/edit")
    public String getPost(@PathVariable long id, Model model){
        model.addAttribute("createdPost", postDao.getPostById(id));
        return "/posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String editPost(@Valid@ModelAttribute Post post, Errors validation, Model model){
        if (validation.hasErrors()){
            model.addAttribute("errors", validation);
            model.addAttribute("createdPost", post);
            return "/posts/edit";
        }
        post.setOwner((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        postDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/delete")
    public String getPostDelete(@PathVariable long id){
        postDao.deleteById(id);
        return "redirect:/posts";
    }

}
