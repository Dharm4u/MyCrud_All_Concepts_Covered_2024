package com.example.demo.Service.Impl;

import com.example.demo.Dto.PostDto;
import com.example.demo.Dto.PostResponse;
import com.example.demo.Entity.PostEntity;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Repository.PostRepository;
import com.example.demo.Service.PostService;
import com.example.demo.Utils.MapperUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public PostDto createPost(PostDto postDto) {
        //convert the DTO to Entity... for this we will use ModelMapper library and add dependency into the POM

        PostEntity post = MapperUtility.mapToPostEntity(postDto);
        //save the data into entity

        PostEntity postEntity = postRepository.save(post);

        //convert entity to DTO and will send the data to the user

        return MapperUtility.mapToPostDto(postEntity); //again converting data into postDto because we dont want to expose entity to the user
    }// GOlden rule in which state we are getting request in the same state we will send the request back to the user or client

    //i.e. PostDto
    @Override
    public PostDto getPostById(Long id) {
        PostEntity postEntity = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", id)); //herer user give worng id ex -5 or id which dosent exist so for this we need to handle exception
        return MapperUtility.mapToPostDto(postEntity);

    }

    @Override
    public PostDto updatePostById(Long id, PostDto postDto) {
        PostEntity postEntity = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", id));
        // postEntity.setTitle(postDto.getTitle()); title is commented because it is unique field we should not update it , alredy mnetioned in entity
        postEntity.setDescription(postDto.getDescription());// lets say if we have 10000 field than we can create a utility class and add all and set 10000 fields and whenever needed use that utility written logic only everytime
        postEntity.setContent(postDto.getContent());
        PostEntity updatedData = postRepository.save(postEntity);

        return MapperUtility.mapToPostDto(updatedData);
    }

    @Override
    public PostResponse getPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<PostEntity> all = postRepository.findAll(pageable);

        List<PostDto> list = all.stream().map(MapperUtility::mapToPostDto).toList();//here we are creating stream in order to map the data from entity to dto, because we need to show dto only but not direct entity that y this convertion happening

        PostResponse postResponse = new PostResponse();
        postResponse.setContents(list);
        postResponse.setPageNo(all.getNumber());
        postResponse.setPageSize(all.getSize());
        postResponse.setTotalPages(all.getTotalPages());
        postResponse.setTotalElements(all.getTotalElements());
        postResponse.setLast(all.isLast());
        return postResponse;
    }

    @Override
    public void deletePost(long id) {
        PostEntity post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);

    }


}
