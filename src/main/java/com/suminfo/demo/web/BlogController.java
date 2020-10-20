package com.suminfo.demo.web;

import com.suminfo.demo.po.Blog;
import com.suminfo.demo.po.User;
import com.suminfo.demo.service.BlogService;
import com.suminfo.demo.service.TagService;
import com.suminfo.demo.service.TypeService;
import com.suminfo.demo.vo.BlogQuery;
import com.sun.org.apache.regexp.internal.REUtil;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final  String INPUT= "admin/blogs-input";
    private static final  String LIST= "admin/blogs";
    private static final  String REDIRECT_LIST= "redirect:/admin/blogs";

    @Resource
    private TagService tagService;

    @Resource
    private BlogService blogService;

    @Resource
    private TypeService typeService;


    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size=3,sort={"updateTime"},direction = Sort.Direction.DESC)Pageable pageable, BlogQuery blog, Model model){
            model.addAttribute("tags",tagService.listTag());
            model.addAttribute("types", typeService.listType());
            model.addAttribute("page",blogService.listBlog(pageable,blog));
        return  LIST;
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

    @GetMapping("/blogs/{id}/input")
    public String editInput(Model model,@PathVariable Long id){
        setTypeAndTag(model);

        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog",blog);
        return  INPUT;
    }
    private void setTypeAndTag(Model model){
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());

    }

    @GetMapping("/blogs/input")
    public String input(Model model, @PathVariable Long id){
        setTypeAndTag(model);

        model.addAttribute("blog",blogService.getBlog(id));
        return  INPUT;
    }
    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session){

        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));
        Blog b = blogService.saveBlog(blog);
        if(b==null){
            attributes.addFlashAttribute("message","操作失败");
        }else {
            attributes.addFlashAttribute("message","操作成功");

        }


        return REDIRECT_LIST;
    }


}
