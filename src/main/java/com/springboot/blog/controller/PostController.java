package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    // fetch all posts from database. return Json object
    @GetMapping
    public List<PostDto> getAllPosts(){
        return postService.getAllPosts();
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name="id")Long id){
        return ResponseEntity.ok(postService.getPostsById(id));
    }
    // create a blog post rest api
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<PostDto>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable(name="id")Long id){
        PostDto postResponse = postService.updatePost(postDto,id);
        return new ResponseEntity<PostDto>(postResponse,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PostDto> deletePost(@PathVariable(name="id")Long id){
        PostDto deleteResponse = postService.deletePost(id);
        return new ResponseEntity<PostDto>(deleteResponse,HttpStatus.OK);
    }
}
