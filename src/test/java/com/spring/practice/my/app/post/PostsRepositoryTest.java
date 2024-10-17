package com.spring.practice.my.app.post;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Streamable;

import java.util.List;

import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostsRepositoryTest {

    @Autowired PostsRepository postsRepository;

    @BeforeEach
    void init(){
        postsRepository.deleteAllInBatch();
    }

    @Test
    void findByTitle(){
        createPost("title", "content1");
        createPost("title", "content2");
        createPost("title2", "content3");
        List<Post> all = postsRepository.findAll();

        Streamable<Post> title = postsRepository.findByTitle("title");
        List<PostDto> list = title.stream().map(post -> new PostDto(post.getId(), post.getTitle(), post.getContent())).toList();
        Assertions.assertThat(list).hasSize(2)
                .extracting("title", "content")
                .containsExactlyInAnyOrder(
                        tuple("title", "content1"),
                        tuple("title", "content2")
                );
    }

    void createPost(String title, String content){
        postsRepository.save(new Post(title, content));
    }
}