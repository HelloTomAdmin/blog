package com.suminfo.demo.service;

import com.suminfo.demo.po.Blog;
import com.suminfo.demo.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {
    Blog getBlog(Long id);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    Page<Blog> listBlog(String query, Pageable pageable);

    Page<Blog> listBlog(Pageable pageable);

    /**
     * 根据tag 标签id 查询博客
     *
     * @param tagId
     * @param pageable
     * @return
     */
    Page<Blog> listBlog(Long tagId, Pageable pageable);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id, Blog blog);

    void deleteBlog(Long id);


    List<Blog> listRecommendBlogTop(Integer size);

    /**
     * 根据id 将markDown 语法转化为html
     *
     * @param id
     * @return
     */
    public Blog getAndConvert(Long id);

    /**
     * 查询归档列表
     */
    Map<String, List<Blog>> archiveBlog();

    /**
     * 查询blog条数
     * @return
     */
    Long countBlog();


}
