package com.suminfo.demo.web;

import com.suminfo.demo.po.Blog;
import com.suminfo.demo.service.BlogService;
import com.suminfo.demo.service.TagService;
import com.suminfo.demo.service.TypeService;
import com.suminfo.demo.vo.BlogQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

@Controller
public class IndexController {

    @Resource
    private BlogService blogService;

    @Resource
    private TypeService typeService;

    @Resource
    private TagService tagService;



    @GetMapping("/")
    public String index(@PageableDefault(size=3,sort={"updateTime"},
            direction = Sort.Direction.DESC)Pageable pageable, Model model){
        model.addAttribute("page", blogService.listBlog(pageable));
        model.addAttribute("types",typeService.listTypeTop(6));
        model.addAttribute("tags",tagService.listTagTop(10));

        model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(8));

        return "index";
    }

    @GetMapping("/blog")
    public String blog(){
        return "blog";

    }

}
