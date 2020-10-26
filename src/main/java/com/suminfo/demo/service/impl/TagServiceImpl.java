package com.suminfo.demo.service.impl;


import com.suminfo.demo.Exception.NoFoundException;
import com.suminfo.demo.dao.TagRepository;
import com.suminfo.demo.po.Tag;
import com.suminfo.demo.service.TagService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TagServiceImpl implements TagService {
    @Resource
    private TagRepository repository;

    @Override
    public Tag saveTag(Tag tag) {
        return repository.save(tag);
    }

    @Override
    public Tag getTag(Long id) {
        return repository.getOne(id);
    }

    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Tag updateTag(Long id, Tag tag) {
        com.suminfo.demo.po.Tag one = repository.getOne(id);
        if(one==null){
            throw new   NoFoundException("找不到该标签");
        }
        BeanUtils.copyProperties(tag,one);

        return repository.save(one);
    }

    @Override
    public void deleteTag(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Tag> listTag() {
        return repository.findAll();
    }

    @Override
    public List<Tag> listTag(String ids) {
        List<Tag>tags=new ArrayList<>();
        if(StringUtils.isNotEmpty(ids)){
            String[] split = ids.split(",");
            List<String> list = Arrays.asList(split);
            List<Long> collect = list.stream().filter(a->StringUtils.isNotEmpty(a)).map(a -> Long.parseLong(a)).collect(Collectors.toList());
//            tags=repository.findAll(collect);
            tags = repository.findAllById(collect);
        }

        return tags;
    }

    @Override
    public List<Tag> listTagTop(Integer size) {


        Sort sort=Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable=PageRequest.of(0,size,sort);
        return repository.findTop(pageable);
    }
}
