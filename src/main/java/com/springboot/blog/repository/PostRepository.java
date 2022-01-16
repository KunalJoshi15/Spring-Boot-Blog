package com.springboot.blog.repository;

import com.springboot.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
// there are many default methods which are defined insite the JpdRepository those methods we can not directly use using this.
// here we will not have to add @Repository annotation as its parent has it.
public interface PostRepository extends JpaRepository<Post,Long> {
}
