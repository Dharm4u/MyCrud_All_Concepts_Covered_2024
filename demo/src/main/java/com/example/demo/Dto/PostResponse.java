package com.example.demo.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class PostResponse {

    private List<PostDto> contents;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;

    private boolean last;
}
