package com.spring.practice.my.app.post.adapter;

import com.spring.practice.my.app.post.Post;
import com.spring.practice.my.app.post.PostDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.OperationNotSupportedException;
import java.util.List;

@RestController
class PostsController {


    @GetMapping("/posts")
    ResponseEntity<List<Post>> findAll(){
        PostDto dto = null;
        throw new IllegalStateException();
    }
}
