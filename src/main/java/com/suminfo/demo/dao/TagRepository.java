package com.suminfo.demo.dao;

import com.suminfo.demo.po.Tag;
import com.suminfo.demo.po.Type;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag,Long> {
    Tag findByName(String name);

    @Query("select  t from  Tag  t")
    List<Tag>findTop(Pageable pageable);
}
