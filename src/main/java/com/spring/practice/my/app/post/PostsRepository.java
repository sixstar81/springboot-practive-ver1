package com.spring.practice.my.app.post;

import com.spring.practice.my.app.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;

public interface PostsRepository extends JpaRepository<Post, Long> {
    Streamable<Post> findByTitle(String title);
}