package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    // fetch all posts from database. return Json object
    // there are some more details we need to send to the client.
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(name="pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(name="pageSize",defaultValue = "10",required = false) int pageSize,
            @RequestParam(name="sortBy",defaultValue="id",required=false) String sortBy,
            @RequestParam(name="sortDir",defaultValue = "asc",required = false) String sortDir
    ){
        return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name="id")Long id){
        return ResponseEntity.ok(postService.getPostsById(id));
    }
    @PreAuthorize("hasRole('ADMIN')")
    // create a blog post rest api
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<PostDto>(postService.createPost(postDto), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public  ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,@PathVariable(name="id")Long id){
        PostDto postResponse = postService.updatePost(postDto,id);
        return new ResponseEntity<PostDto>(postResponse,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<PostDto> deletePost(@PathVariable(name="id")Long id){
        PostDto deleteResponse = postService.deletePost(id);
        return new ResponseEntity<PostDto>(deleteResponse,HttpStatus.OK);
    }
}
