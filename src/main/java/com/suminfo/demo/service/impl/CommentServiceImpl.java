package com.suminfo.demo.service.impl;

import com.suminfo.demo.dao.CommentRepository;
import com.suminfo.demo.po.Comment;
import com.suminfo.demo.service.BlogService;
import com.suminfo.demo.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentRepository commentRepository;




    @Override
    public List<Comment> listCommentByBlogId(Long id) {
        Sort sort = Sort.by(Sort.Direction.ASC,"createTime");

        List<Comment> comments = commentRepository.findByBlogIdAndParentCommentNull(id, sort);


        return eachComment(comments);
    }

    @Transactional
    @Override
    public Comment saveComment(Comment comment) {

        Long parentCommentId = comment.getParentComment().getId();



        if(-1!=parentCommentId){
            comment.setParentComment(commentRepository.getOne(parentCommentId));
        }else{
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());



        return commentRepository.save(comment);


    }

    private List<Comment>eachComment(List<Comment>comments){
        List<Comment>commentsView=new ArrayList<>();
        for (Comment comment : comments) {
            Comment c=new Comment();
            BeanUtils.copyProperties(comment,c);
            commentsView.add(c);
        }
        combineChildren(commentsView);
        return commentsView;

    }

    /**
     * root 根节点 ，blog 不为空的对象集合
     * @param comments
     */
    private void combineChildren(List<Comment> comments){
        for (Comment comment : comments) {
            List<Comment> replyComments = comment.getReplyComments();
            for (Comment replyComment : replyComments) {
                recursively(replyComment);
            }

            //修改顶级节点的reply集合为迭代处理后的集合
            comment.setReplyComments(tempReplys);

            //清理临时存放区域
            tempReplys=new ArrayList<>();
        }
    }

    private List<Comment> tempReplys=new ArrayList<>();

    /**
     * 递归迭代
     * @param replyComment
     */
    private void recursively(Comment replyComment) {
        tempReplys.add(replyComment);//顶级节点添加到临时存放集合
        if(replyComment.getReplyComments().size()>0){
            List<Comment> replyComments = replyComment.getReplyComments();
            for (Comment comment : replyComments) {
                tempReplys.add(comment);
                if(comment.getReplyComments().size()>0){
                    recursively(comment);
                }
            }

        }

    }
}
