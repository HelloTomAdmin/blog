package com.suminfo.demo.service.impl;

import com.suminfo.demo.Exception.NoFoundException;
import com.suminfo.demo.dao.TypeRespository;
import com.suminfo.demo.po.Type;
import com.suminfo.demo.service.TypeService;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class TypeServiceImpl implements TypeService {


    @Resource
    private TypeRespository typeRespository;

    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeRespository.save(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeRespository.getOne(id);
    }
    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRespository.findAll(pageable);
    }
    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type t =typeRespository.getOne(id);
        if(t==null){
            throw new NoFoundException("不存在该类型");
        }

        BeanUtils.copyProperties(type,t);
        return typeRespository.save(t);
    }
    @Transactional
    @Override
    public void deleteType(Long id) {
        typeRespository.deleteById(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeRespository.findByName(name);
    }
}
