package com.springboot.blog.service;

import com.springboot.blog.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long post_id,CommentDto commentDto);
    List<CommentDto> getCommentByPostId(long postId);
    CommentDto getCommentById(Long postId,Long commentId);
    CommentDto updateCommentById(Long postId,Long commentId,CommentDto commentRequest);
    CommentDto deleteComment(Long postId,Long commentId);
}
