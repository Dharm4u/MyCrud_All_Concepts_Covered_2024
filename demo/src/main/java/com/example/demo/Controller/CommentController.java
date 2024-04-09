package com.example.demo.Controller;


import com.example.demo.Dto.CommentDto;
import com.example.demo.Entity.CommentEntity;
import com.example.demo.Service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class CommentController {

    private final CommentService commentService; //here creating fail safe mechanism to avoid 500 errors

    public CommentController(CommentService commentService) { // after spring 3 this constructor is reponsible to do to Autowiring functionality so need to use autowired this constructor logic will do same thing for you
        this.commentService = commentService;
    }

    //complete URl will be like localhost:8080/api/post/{postId}/comments
    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") Long postId,
                                                    @RequestBody CommentDto commentDto) {

        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);


    }

    //spring boot REST api for get comment by id
    @GetMapping("/{id}")
    public CommentDto getCommentById(
            @PathVariable("postId") long postId,
            @PathVariable("id") long id
    ){
        return commentService.getCommentById(postId, id);
    }

    //get comments using postId api

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentDto>> findByPostId(@PathVariable(value = "postId") Long postId) {

        return ResponseEntity.ok(commentService.findByPostId(postId));
    }

    //spring boot REST api for update comment
    @PutMapping("/{id}")
    public CommentDto updateComment(
            @PathVariable("postId") long postId,
            @PathVariable("id") long id,
            @Valid @RequestBody CommentDto commentDto
    ){
        return commentService.updateComment(postId, id, commentDto);
    }

    //spring boot REST api for delete comment
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(
            @PathVariable("postId") long postId,
            @PathVariable("id") long id
    ){
        commentService.deleteComment(postId, id);
        return new ResponseEntity("Comment deleted successfully",HttpStatus.OK);
    }
    //cheking
}
