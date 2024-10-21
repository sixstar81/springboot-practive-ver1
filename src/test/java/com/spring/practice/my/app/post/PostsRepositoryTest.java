package com.spring.practice.my.app.post;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
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
        createPost("title", "content2", 1, false);
        createPost("title", "content3",3, true);

        int numberOfActivePost = postsRepository.findNumberOfActivePost(true, 1);
        assertThat(numberOfActivePost).isEqualTo(1);

        List<Post> postOfTitleAndLevel = postsRepository.findByTitleAndLevel("title", 1);
        assertThat(postOfTitleAndLevel).hasSize(2)
                .extracting("content")
                .containsExactlyInAnyOrder("content1", "content2");

        List<Post> postOfTitleAndLevelOfNative = postsRepository.findByTitleAndLevelOfNative("title", 1);
        System.out.println(postOfTitleAndLevelOfNative);
        assertThat(postOfTitleAndLevelOfNative).hasSize(2)
                .extracting("content")
                .containsExactlyInAnyOrder("content1", "content2");

    }

    @Test
    void findByArraySort(){
        createPost("title1", "content1", 1, true);
        createPost("title2", "content2", 2, false);
        createPost("title1-2", "content2", 1, false);
        createPost("a-title1-2", "content2", 1, false);
        createPost("pages", "content3",3, true);

        List<Object[]> results = postsRepository.findByAsArrayAndSort("title", 1, Sort.by("title"));
        //[{"a-title1-2", "content2", "1"},{"title1", "content1", 1}, {"title1-2", "content2", 1}]
        List<PostResponse> results2 = postsRepository.findByAsArrayAndSort2("title", 1, Sort.by("title"));

        results.forEach(objects -> System.out.println(objects[0] + "/" + objects[1] + "/" + objects[2]));
        results2.forEach(System.out::println);

        assertThat(results.get(0)[0]).isEqualTo("a-title1-2");
        assertThat(results.get(1)[0]).isEqualTo("title1");
        assertThat(results.get(2)[1]).isEqualTo("content2");
        assertThat(results2).hasSize(3)
                .extracting("title", "content", "level")
                .containsExactlyInAnyOrder(
                        tuple("a-title1-2", "content2", 1),
                        tuple("title1", "content1", 1),
                        tuple("title1-2", "content2", 1)
                        );

    }

    void createPost(String title, String content, int level, boolean active){
        postsRepository.save(new Post(title, content, level, active));
    }
}