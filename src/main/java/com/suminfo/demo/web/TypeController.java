package com.suminfo.demo.web;

import com.suminfo.demo.po.Type;
import com.suminfo.demo.service.TypeService;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.naming.Binding;
import javax.validation.Valid;

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
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/type-input";
    }

    @GetMapping("/types/{id}/input")
    public String editInput (@PathVariable Long id,Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/type-input";
    }


    @PostMapping("/types")
    public String post(@Valid com.suminfo.demo.po.Type type, BindingResult result, RedirectAttributes attributes){
        Type typeByName = typeService.getTypeByName(type.getName());
        if(typeByName!=null){
            result.rejectValue("name","nameError","不能添加重复分类");
        }


        if(result.hasErrors()){
            return "admin/type-input";
        }
        com.suminfo.demo.po.Type t = typeService.saveType(type);
        if(null==t){
            attributes.addFlashAttribute("message","新增失败");
        }else {
            attributes.addFlashAttribute("message","新增成功");

        }
        return "redirect:/admin/types";
    }


    @PostMapping("/types/{id}")
    public String editPost(@Valid com.suminfo.demo.po.Type type, BindingResult result,@PathVariable Long id, RedirectAttributes attributes){
        Type typeByName = typeService.getTypeByName(type.getName());
        if(typeByName!=null){
            result.rejectValue("name","nameError","不能添加重复分类");
        }


        if(result.hasErrors()){
            return "admin/type-input";
        }
        com.suminfo.demo.po.Type t = typeService.updateType(id,type);
        if(null==t){
            attributes.addFlashAttribute("message","更新失败");
        }else {
            attributes.addFlashAttribute("message","更新成功");

        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";

    }



}
