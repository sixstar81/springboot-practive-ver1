package com.spring.practice.my.app.post.adapter;

import com.spring.practice.my.app.post.Post;
import com.spring.practice.my.app.post.PostDto;
import com.spring.practice.my.app.post.PostsService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.OperationNotSupportedException;
import java.util.List;

@RequiredArgsConstructor
@RestController
class PostsController {

    private final PostsService postsService;

    @GetMapping("/posts/{id}")
    ResponseEntity<Post> postBy(@PathVariable Long id) {
        return ResponseEntity.ok(postsService.findById(id));
    }

    @PostMapping("/posts")
    ResponseEntity<Post> register(@RequestBody PostRequest request){
        Post saved = postsService.save(new PostDto(null, request.title(), request.content()));
        return ResponseEntity.status(201).body(saved);
    }
}
