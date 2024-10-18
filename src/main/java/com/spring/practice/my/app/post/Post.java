package com.spring.practice.my.app.post;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@NoArgsConstructor
@Setter
@Getter
@Table(name = "POSTS")
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String title;
    String content;
    int level;
    boolean active;
    LocalDateTime registerDate;

    @Builder
    Post(String title, String content, int level, boolean active){
        this.title = title;
        this.content = content;
        this.level = level;
        this.active = active;
        this.registerDate = LocalDateTime.now();
    }
}
