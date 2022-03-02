package com.springboot.blog.payload;

import com.springboot.blog.entity.Comment;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class PostDto {
    private Long id;
    @NotEmpty
    @Size(min = 10,message = "Post Description should have at least 10 characters")
    private String description;
    @NotEmpty
    @Size(min = 2,message = "Post message should have at least 2 characters")
    private String title;
    private String content;
    private Set<CommentDto> comments;
}
