package com.example.spring_intro.repository;

import com.example.spring_intro.model.entity.UserComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCommentRepo extends JpaRepository<UserComment, Long> {
    UserComment findByAuthor(String userName);
}
