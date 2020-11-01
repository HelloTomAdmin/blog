package com.suminfo.demo.service;

import com.suminfo.demo.po.Blog;
import com.suminfo.demo.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {
    Blog getBlog(Long id);

    Page<Blog> listBlog (Pageable pageable, BlogQuery blog);

    Page<Blog>listBlog(String query,Pageable pageable);

    Page<Blog>listBlog(Pageable pageable);

    Blog saveBlog (Blog blog);

    Blog updateBlog(Long id,Blog blog);

    void deleteBlog(Long id);



    List<Blog> listRecommendBlogTop(Integer size);

    /**
     * 根据id 将markDown 语法转化为html
     * @param id
     * @return
     */
    public Blog getAndConvert(Long id);

}
