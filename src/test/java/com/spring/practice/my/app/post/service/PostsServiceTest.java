package com.spring.practice.my.app.post.service;

import com.spring.practice.my.app.post.Post;
import com.spring.practice.my.app.post.PostsRepository;
import com.spring.practice.my.app.post.PostsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostsServiceTest {

    @Autowired
    PostsService postsService;

    @Autowired
    PostsRepository postsRepository;

    @BeforeEach
    void reset(){
        postsRepository.deleteAllInBatch();
    }

    @Test
    void updatedPost(){
        //given
        postLoads();
        //when
        PostDto updateDto = new PostDto(1L, "title2", "content2");
        postsService.save(updateDto);

        //then
        Post updatedEntity = postsService.findById(1L);
        assertThat(updatedEntity.getTitle()).isEqualTo("title2");
        assertThat(updatedEntity.getContent()).isEqualTo("content2");
    }

    @Test
    void save(){
        //when
        Post saved = postsService.save(new PostDto(null, "testTitle", "testContent"));
        //then
        Post byId = postsService.findById(saved.getId());
        System.out.println(byId);
        assertThat(byId.getId()).isNotNull();
    }

    void postLoads(){
        PostDto dto1 = new PostDto(null, "title1", "content1");
        PostDto dto2 = new PostDto(null, "title2", "content2");
        PostDto dto3 = new PostDto(null, "title3", "content3");
        postsService.save(dto1);
        postsService.save(dto2);
        postsService.save(dto3);
    }
}