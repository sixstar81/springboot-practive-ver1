package com.spring.practice.my.app.post.service;

import com.spring.practice.my.app.post.Post;
import com.spring.practice.my.app.post.PostDto;
import com.spring.practice.my.app.post.adapter.PostsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
class PostsService {

    private final PostsRepository postsRepository;

    List<Post> findAll(){
        return postsRepository.findAll();
    }

    Post findById(Long id){
        Optional<Post> post = postsRepository.findById(id);
        if(post.isEmpty()) throw new IllegalStateException("not exists post");
        return post.get();
    }

    // update or insert
    Post save(PostDto dto){

        if(dto.id()==null) {
            return insert(dto);
        }

        Optional<Post> post = postsRepository.findById(dto.id());
        if(post.isPresent()){
            Post updatePost = post.get();
            updatePost.setTitle(dto.title());
            updatePost.setContent(dto.content());
            return updatePost;
        }

        throw new IllegalStateException("failed saved post by not exists id[" +dto.id()+  "]  ");
    }

    private Post insert(PostDto dto) {
        return postsRepository.save(Post.builder().title(dto.title()).content(dto.content()).build());
    }
}

