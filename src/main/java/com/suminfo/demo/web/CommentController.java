package com.suminfo.demo.web;

import com.suminfo.demo.po.Comment;
import com.suminfo.demo.service.BlogService;
import com.suminfo.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CommentController {


    @Resource
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    @Resource
    private CommentService commentService;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model){

        return "blog :: commentList";



    }


    @PostMapping("/comments")
    public String post(Comment comment){
        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.getBlog(blogId));
        comment.setAvatar(avatar);
        commentService.saveComment(comment);


        return "redirect:/comments/"+blogId;
    }



}
