package com.suminfo.demo.web;

import com.suminfo.demo.po.Blog;
import com.suminfo.demo.service.BlogService;
import com.suminfo.demo.service.TypeService;
import com.suminfo.demo.vo.BlogQuery;
import com.sun.org.apache.regexp.internal.REUtil;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Pageable;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Resource
    private BlogService blogService;

    @Resource
    private TypeService typeService;


    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size=3,sort={"updateTime"},direction = Sort.Direction.DESC)Pageable pageable, BlogQuery blog, Model model){
            model.addAttribute("types", typeService.listType());
            model.addAttribute("page",blogService.listBlog(pageable,blog));


        return  "admin/blogs";
    }

    /**
     * 搜索部分更新
     * @param pageable
     * @param blog
     * @param model
     * @return
     */
    @RequestMapping("/blogs/search")
    public String search(@PageableDefault(size=2,sort={"updateTime"},direction = Sort.Direction.DESC)Pageable pageable, BlogQuery blog, Model model){
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return  "admin/blogs :: blogList";
    }

}
