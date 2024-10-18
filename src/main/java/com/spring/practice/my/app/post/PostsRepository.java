package com.spring.practice.my.app.post;

import com.spring.practice.my.app.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.util.Streamable;

public interface PostsRepository extends JpaRepository<Post, Long> {
    Streamable<Post> findByTitle(String title);
    Streamable<Post> findByContentContaining(String content);

    @Query("select count(u) from #{#entityName} u where u.level = ?2 and u.active= ?1")
    int findNumberOfActivePost(boolean active, int level);


}