package com.spring.practice.my.app.post;

import com.spring.practice.my.app.post.service.PostDto;

import java.util.List;

public interface PostsService {

    List<Post> findAll();

    Post findById(Long id);

    // update or insert
    Post save(PostDto dto);
}