package com.example.demo.Repository;

import com.example.demo.Entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    List<CommentEntity> findByPostId(Long postId);
    //when we define this method JPA framework will automatically
    //create implementatio on behalf of you
    //we cant alter this syntax or rename methof name it will surely throw an error compile time error

    Boolean existsByPostId(Long postId);

}
