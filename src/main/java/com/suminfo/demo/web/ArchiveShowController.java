package com.suminfo.demo.web;

import com.suminfo.demo.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

@Controller
public class ArchiveShowController {


    @Resource
    private BlogService blogService;

    @GetMapping("/archievs")
    public String archives(Model model){
        model.addAttribute("archiveMap",blogService.archiveBlog());
        model.addAttribute("blogCount",blogService.countBlog());
        return "archievs";
    }
}
