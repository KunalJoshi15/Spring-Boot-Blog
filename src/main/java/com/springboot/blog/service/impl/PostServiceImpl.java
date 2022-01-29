package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    // constructor based dependency injection is being used.
    private PostRepository postRepository;
    // here @Autowired annotation can be omitted.
    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto getPostsById(Long id) {
        return mapToDto(postRepository.getById(id));
    }

    public List<PostDto> getAllPosts(){
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post->mapToDto(post)).collect(Collectors.toList());
    }
    @Override
    public PostDto createPost(PostDto postDto) {
        // Convert DTO to repository
        Post post = DtoToMap(postDto);
//
        Post newPost = postRepository.save(post);
//         convert entity to DTO
//        System.out.println(newPost);
        PostDto postResponse = mapToDto(newPost);
        return postResponse;
    }

    @Override
    public PostDto deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Delete post","id",""+id));
        postRepository.deleteById(id);
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {
        // get post by id from database.
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",""+id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post updatedPost = postRepository.save(post);
        return mapToDto(updatedPost);
    }

    private Post DtoToMap(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }
    private PostDto mapToDto(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setContent(post.getContent());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        return postDto;
    }
}
