package com.spring.practice.my.app.post;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Streamable;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
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
        createPost("title", "content1", 1, true);
        createPost("title", "content2", 2, false);
        createPost("title2", "content3",3, true);

        Streamable<Post> title = postsRepository.findByTitle("title");
        List<PostDto> list = title.stream().map(post -> new PostDto(post.getId(), post.getTitle(), post.getContent())).toList();
        Assertions.assertThat(list).hasSize(2)
                .extracting("title", "content")
                .containsExactlyInAnyOrder(
                        tuple("title", "content1"),
                        tuple("title", "content2")
                );
    }

    @Test
    void findByLevelAndActive(){
        createPost("title", "content1", 1, true);
        createPost("title", "content2", 2, false);
        createPost("title2", "content3",3, true);

        int numberOfActivePost = postsRepository.findNumberOfActivePost(true, 1);
        assertThat(numberOfActivePost).isEqualTo(1);
    }

    void createPost(String title, String content, int level, boolean active){
        postsRepository.save(new Post(title, content, level, active));
    }
}