package com.suminfo.demo.dao;

import com.suminfo.demo.po.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRespository extends JpaRepository<Type,Long> {

    Type findByName(String name);
}
