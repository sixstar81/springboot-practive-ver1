package com.spring.practice.my.app.post.service;

import com.spring.practice.my.app.post.Post;
import com.spring.practice.my.app.post.PostDto;
import com.spring.practice.my.app.post.service.PostsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostsServiceTest {

    @Autowired
    PostsService postsService;

    @Test
    void updatedPost(){
        
        PostDto dto = new PostDto(null, "title", "content");
        Post saved = postsService.save(dto);

        PostDto updateDto = new PostDto(saved.getId(), "title2", "content2");
        postsService.save(updateDto);

        Post updatedEntity = postsService.findById(saved.getId());

        assertThat(updatedEntity.getTitle()).isEqualTo("title2");
        assertThat(updatedEntity.getContent()).isEqualTo("content2");
    }
}