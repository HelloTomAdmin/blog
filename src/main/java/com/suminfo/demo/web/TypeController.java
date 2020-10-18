package com.suminfo.demo.web;

import com.suminfo.demo.service.TypeService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin")
public class TypeController {
    @Resource
    private TypeService typeService;


    @GetMapping("/types")
    public String list(@PageableDefault(size=2,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model){

        model.addAttribute("page",typeService.listType(pageable));
        return "admin/types";

    }

    @GetMapping("/types/input")
    public String input(){
        return "admin/type-input";
    }


    @PostMapping("/types")
    public String post(com.suminfo.demo.po.Type type, RedirectAttributes attributes){
        com.suminfo.demo.po.Type t = typeService.saveType(type);
        if(null==t){
            attributes.addFlashAttribute("message","操作失败");
        }else {
            attributes.addFlashAttribute("message","操作成功");

        }
        return "redirect:/admin/types";
    }
}
