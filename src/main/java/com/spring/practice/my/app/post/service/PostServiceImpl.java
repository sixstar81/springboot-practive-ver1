package com.spring.practice.my.app.post.service;

import com.spring.practice.my.app.post.Post;
import com.spring.practice.my.app.post.PostDto;
import com.spring.practice.my.app.post.PostsRepository;
import com.spring.practice.my.app.post.PostsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
class PostServiceImpl implements PostsService {

    final PostsRepository postsRepository;

    @Override
    public List<Post> findAll(){
        return postsRepository.findAll();
    }

    public Post findById(Long id){
        Optional<Post> post = postsRepository.findById(id);
        if(post.isEmpty()) throw new IllegalStateException("not exists post");
        return post.get();
    }

    // update or insert
    public Post save(PostDto dto){

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
