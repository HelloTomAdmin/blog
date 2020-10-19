//package com.suminfo.demo.Controller;
//
//import com.suminfo.demo.Exception.NoFoundException;
//import com.suminfo.demo.po.Blog;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//public class IndexController {
//
//    private Logger logger= LoggerFactory.getLogger(this.getClass());
//
//
////    @RequestMapping("/")
////    public String index(){
//////        int i =9/0;
////        String blog= null;
////        if(blog==null){
////            throw new  NoFoundException("博客不存在");
////        }
////        return "index";
////    }
//
//    @RequestMapping("/{id}/{name}")
//    public String blog(@PathVariable Integer id,@PathVariable String name){
//        logger.info("123");
//        System.out.println("测试");
//        System.out.println("----------index-----------");
//        Blog blog =new Blog();
//
//        return "index";
//    }
//}
