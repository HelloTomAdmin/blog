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
import org.springframework.web.bind.annotation.*;

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

    /**
     * 根据id 找到博客对象
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model){
        model.addAttribute("blog",blogService.getAndConvert(id));


        return "blog";

    }

    @GetMapping("/tags")
    public String tags(){
        return "tags";

    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }
    @GetMapping("/types")
    public String types(){
        return "types";
    }
    @GetMapping("/archievs")
    public String archievs(){
        return "archievs";
    }
    @GetMapping("/blogIndex")
    public String blogs(){
        return "blog";
    }


    @PostMapping("/search")
    public String search(@PageableDefault(size=8,sort={"updateTime"},
            direction = Sort.Direction.DESC)Pageable pageable,@RequestParam String query, Model model){
        model.addAttribute("page",blogService.listBlog("%"+query+"%",pageable));
        model.addAttribute("query",query);


        return "search";
    }


}
