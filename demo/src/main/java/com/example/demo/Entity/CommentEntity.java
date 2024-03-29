package com.example.demo.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "comments")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    private String body;
    @ManyToOne(fetch = FetchType.LAZY)
// here LAZy menas when there is a requirmentt then only it will be fetched and EAGER menas alredy available
    @JoinColumn(name = "post_id", nullable = false) //this is foriegn key
    private PostEntity post;
}
