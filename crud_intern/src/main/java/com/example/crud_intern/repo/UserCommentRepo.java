package com.example.crud_intern.repo;

import com.example.crud_intern.entity.UserComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCommentRepo extends JpaRepository<UserComment, Long> {
    UserComment findByAuthor(String userName);
}
