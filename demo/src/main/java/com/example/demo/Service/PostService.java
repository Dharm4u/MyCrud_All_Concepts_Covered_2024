package com.example.demo.Service;

import com.example.demo.Dto.PostDto;
import com.example.demo.Dto.PostResponse;


public interface PostService {

    //create new post
    PostDto createPost(PostDto postDto);

    //get post by id
    PostDto getPostById(Long id);

    //update post by id
    PostDto updatePostById(Long id, PostDto postDto);


    //get All the posts
    PostResponse getPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    void deletePost(long id);
}
