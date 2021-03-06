package com.springboot.blog.controller;

import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postid}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postid") Long postid,@Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postid,commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") Long postId){
        return commentService.getCommentByPostId(postId);
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") Long postId, @PathVariable(value = "commentId") Long commentId){
        return new ResponseEntity<CommentDto>(commentService.getCommentById(postId,commentId),HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateCommentById(@Valid @RequestBody CommentDto commentDto, @PathVariable(value="postId") Long postId, @PathVariable(value="commentId") Long commentId){
        return new ResponseEntity<>(commentService.updateCommentById(postId,commentId,commentDto),HttpStatus.OK);
    }

    @RequestMapping(value="/posts/{postId}/comments/{commentId}", method=RequestMethod.DELETE)
    public ResponseEntity<CommentDto> deleteCommentById(@PathVariable(value = "postId") Long postId,@PathVariable(value = "commentId") Long commentId){
        return new ResponseEntity<>(commentService.deleteComment(postId,commentId),HttpStatus.OK);
    }
}
