package com.suminfo.demo.service;

import com.suminfo.demo.po.Comment;

import java.util.List;

/**
 * 评论接口
 */
public interface CommentService {
    /**
     * 根据id查询列表
     * @param id
     * @return
     */
    List<Comment> listCommentByBlogId(Long id);

    /**
     * 保存评论
     * @param comment
     * @return
     */
    Comment saveComment(Comment comment);

}
