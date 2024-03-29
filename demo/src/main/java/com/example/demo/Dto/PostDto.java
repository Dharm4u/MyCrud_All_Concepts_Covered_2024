package com.example.demo.Dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostDto {

    private Long id;

    @NotEmpty
    @Size(min = 2, message = "Post title should have 2 characters minimum")
    private String title;
    @NotEmpty
    @Size(min = 10, message = "Post description should have 10 characters minimum")
    private String description;
    @NotBlank
    private String content;

}
//What is the purpose of DTO
//ans Data Trasfer Object,   To provide access security for entity class, because we dodnt want to expose entity class directly
// to the user , instead we are exposing a simple class i.e DTO it has no relation with your enity, if a ahcker comes to your application
// he will get  noting just he will be herer only
// One more catch here is we can use single DTO for multiple entity classess
