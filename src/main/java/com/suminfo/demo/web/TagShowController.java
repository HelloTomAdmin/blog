package com.suminfo.demo.web;

import com.suminfo.demo.po.Tag;
import com.suminfo.demo.service.BlogService;
import com.suminfo.demo.service.TagService;
import com.suminfo.demo.vo.BlogQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class TagShowController {
    @Resource
    private TagService TagService;

    @Resource
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String Tags(@PageableDefault(size=8,sort={"updateTime"},
            direction = Sort.Direction.DESC)Pageable pageable, Model model, @PathVariable Long id){
        List<Tag> tags = TagService.listTagTop(10000);

        if(id==-1){
            id=tags.get(0).getId();
        }
        model.addAttribute("tags",tags);
        model.addAttribute("page",blogService.listBlog(id,pageable));
        model.addAttribute("activeTagId",id);
        return "tags";
    }

}
