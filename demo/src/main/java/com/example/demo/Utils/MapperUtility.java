package com.example.demo.Utils;

import com.example.demo.Dto.CommentDto;
import com.example.demo.Dto.PostDto;
import com.example.demo.Entity.CommentEntity;
import com.example.demo.Entity.PostEntity;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

public class MapperUtility {

    @Bean
    static ModelMapper modelMapper() {
        return new ModelMapper();  //creating bean so that spring can manage object of this ModelMapper
    }
    //this method will convert entity to Dto

    public static PostDto mapToPostDto(PostEntity post) {
        return modelMapper().map(post, PostDto.class);

    }

    //this method will convert DTO to entity

    public static PostEntity mapToPostEntity(PostDto postDto) {
        return modelMapper().map(postDto, PostEntity.class);

    }

    //map to commentoDTO
    public static CommentDto mapToCommentDto(CommentEntity commentEntity) {
        return modelMapper().map(commentEntity, CommentDto.class);
    }

    //map to comment ENtity

    public static CommentEntity mapToCommentEntity(CommentDto commentDto) {
        return modelMapper().map(commentDto, CommentEntity.class);
    }

}
