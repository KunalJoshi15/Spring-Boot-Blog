package com.springboot.blog.controller;

import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postid}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postid") Long postid,@RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postid,commentDto), HttpStatus.CREATED);
    }
}
