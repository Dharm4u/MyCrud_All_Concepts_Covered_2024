package com.example.demo.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "posts")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false, unique = true)
    private String title;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "content", nullable = false)
    private String content;
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL, orphanRemoval = true) // this "post here is the variable/feild that we use in post entity class and cascade having multiple enum options and cascadeType all also means whaterver happens with post it should reflect to comment also and orphanRemoval means is post is deleted comments belongs to that post also will be deleted
    private List<CommentEntity> comments;// As we all know that a post can have multiple comments for that ...here we are represting the collection of objects
// here one to  many relation is there thats why we use annotation one to many

}
