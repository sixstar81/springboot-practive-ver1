package com.spring.practice.my.app.post.adapter;

import com.spring.practice.my.app.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Post, Long> {
}
