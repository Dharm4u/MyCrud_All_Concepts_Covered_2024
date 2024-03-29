package com.example.demo.Service.Impl;

import com.example.demo.Dto.CommentDto;
import com.example.demo.Entity.CommentEntity;
import com.example.demo.Entity.PostEntity;
import com.example.demo.Exception.BlogApiException;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Repository.CommentRepository;
import com.example.demo.Repository.PostRepository;
import com.example.demo.Service.CommentService;
import com.example.demo.Utils.MapperUtility;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {

    CommentRepository commentRepository;
    PostRepository postRepository;    // In both the feild we are not using Autowired annotation .. Instead we will use constructor injection
    //below constructor injection behaves same as autowired annotaion or Dependendency injection // this is core java thing


    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        //check if valid return post if not throw exception
        PostEntity post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));

        //save the comment into comment entity by updating the post
        CommentEntity commentEntity = MapperUtility.mapToCommentEntity(commentDto);
        commentEntity.setPost(post);
        CommentEntity created = commentRepository.save(commentEntity);
        return MapperUtility.mapToCommentDto(created);
    }

    @Override
    public List<CommentDto> findByPostId(Long postId) {
        //as always we need to check first weather it is presetn in database or not if not then throw exception
        postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));

        //check comment by postId for scenario of we are trying to fetch post hwich is having no comments to handle this scenario

        if (!commentRepository.existsByPostId(postId))// if comment is not there associated with this postId then throw custom exception by creating   a class and add entry in global exception handler
            throw new BlogApiException("No comments found for the post  id " + postId);

        //get the comments usign postId
        List<CommentEntity> comment = commentRepository.findByPostId(postId);
        return comment.stream().map(MapperUtility::mapToCommentDto).toList(); //returning by converting entity into dto by streaming the data and then using method refrence util convertors

    }

    @Override
    public CommentDto getCommentById(long postId, long id) {
        PostEntity post = findByIdPost(postId);
        CommentEntity comment = findByIdComment(id);

        checkBlogAPIException(post, comment);

        return MapperUtility.mapToCommentDto(comment);
    }

    private boolean checkBlogAPIException(PostEntity post, CommentEntity comment) {
        if (!post.getId().equals(comment.getPost().getId())) {
            throw new BlogApiException("Comment doesn't belong to the post");
        }
        return true;
    }

    private CommentEntity findByIdComment(long id) {
        CommentEntity comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", id)
        );
        return comment;
    }

    private PostEntity findByIdPost(long postId) {
        PostEntity post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId)
        );
        return post;
    }

    @Override
    public CommentDto updateComment(long postId, long id, CommentDto commentDto) {
        PostEntity post = findByIdPost(postId);
        CommentEntity comment = findByIdComment(id);
        checkBlogAPIException(post, comment);
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        CommentEntity updatedComment = saveToDb(comment);
        return MapperUtility.mapToCommentDto(updatedComment);
    }

    private CommentEntity saveToDb(CommentEntity comment) {
        CommentEntity save = commentRepository.save(comment);
        return save;
    }

    @Override
    public void deleteComment(long postId, long id) {
        PostEntity post = findByIdPost(postId);
        CommentEntity comment = findByIdComment(id);
        checkBlogAPIException(post, comment);
        commentRepository.delete(comment);
    }
}
