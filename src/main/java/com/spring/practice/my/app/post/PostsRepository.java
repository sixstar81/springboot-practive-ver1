package com.spring.practice.my.app.post;

import com.spring.practice.my.app.post.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;

import java.util.List;

public interface PostsRepository extends JpaRepository<Post, Long> {
    Streamable<Post> findByTitle(String title);
    Streamable<Post> findByContentContaining(String content);

    @Query("select count(u) from #{#entityName} u where u.level = ?2 and u.active= ?1")
    int findNumberOfActivePost(boolean active, int level);

    //JPQL
    @Query("select u from #{#entityName} u where u.title = :title and u.level = :level")
    List<Post> findByTitleAndLevel(@Param("title")String title,@Param("level")int level);

    //Native Query
    @Query(value = "select * from posts where title = :title and level = :level", nativeQuery = true)
    List<Post> findByTitleAndLevelOfNative(@Param("title")String title,@Param("level")int level);

    //JPQL to Object Array List
    @Query("select u.title, u.content, u.level from Post u where u.title like %?1% and u.level = ?2")
    List<Object[]> findByAsArrayAndSort(String title, int level, Sort sort);

    //JPQL to DTO
    @Query("select new com.spring.practice.my.app.post.PostResponse(u.title, u.content, u.level) from Post u where u.title like %?1% and u.level = ?2")
    List<PostResponse> findByAsArrayAndSort2(String title, int level, Sort sort);

}