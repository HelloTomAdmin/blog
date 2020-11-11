package com.suminfo.demo.service.impl;

import com.suminfo.demo.Exception.NoFoundException;
import com.suminfo.demo.dao.BlogRepository;
import com.suminfo.demo.po.Blog;
import com.suminfo.demo.po.Type;
import com.suminfo.demo.service.BlogService;
import com.suminfo.demo.utils.MarkdownUtils;
import com.suminfo.demo.utils.MyBeanUtils;
import com.suminfo.demo.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Resource
    private BlogRepository blogRepository;


    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getOne(id);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates =new ArrayList<>();
                if(!"".equals(blog.getTitle())&&blog.getTitle()!=null){
                    predicates.add(cb.like(root.<String>get("title"),"%"+blog.getTitle()+"%"));



                }
                if(null!=blog.getTypeId()){
                    predicates.add(cb.equal(root.<Type>get("type").get("id"),blog.getTypeId()));
                }
                if(null!=blog.getRecommend()&&blog.isRecommend()){
                    predicates.add(cb.equal(root.<Boolean>get("recommend"),blog.isRecommend()));
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    @Override
    public Page<Blog> listBlog(String query, Pageable pageable) {
        return blogRepository.findByQuery(query,pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {


        return blogRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        if(blog.getId()!=null){
            blog.setUpdateTime(new Date());
        }else{
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);

        }
        return blogRepository.save(blog);
    }

    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog one = blogRepository.getOne(id);
        if(one!=null){
            throw new NoFoundException("找不到该微博");
        }
        BeanUtils.copyProperties(one,blog, MyBeanUtils.getNullPropertyNames(blog));
        one.setUpdateTime(new Date());
        return blogRepository.save(one);
    }

    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {

        Sort sort=Sort.by(Sort.Direction.DESC,"updateTime");
        Pageable pageable= PageRequest.of(0,size,sort);

        return blogRepository.findTop(pageable);
    }

    @Transactional
    @Override
    public Blog getAndConvert(Long id) {
        Blog one = blogRepository.getOne(id);
        if(one==null){
            throw  new NoFoundException("博客未找到");
        }
        Blog b =new Blog();
        BeanUtils.copyProperties(one,b);
        String content = b.getContent();
        b.setContent(MarkdownUtils.markdownToHtml(content));
        blogRepository.updateViews(id);
        return b;
    }
}
