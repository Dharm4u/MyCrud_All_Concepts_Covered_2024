package com.example.demo.Service;

import com.example.demo.Dto.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(long postId,CommentDto commentDto);
    List<CommentDto> findByPostId(Long postId);

    CommentDto getCommentById(long postId, long id);
    CommentDto updateComment(long postId, long id, CommentDto commentDto);
    void deleteComment(long postId,long id);

}
