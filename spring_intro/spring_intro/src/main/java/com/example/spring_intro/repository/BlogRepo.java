package com.example.spring_intro.repository;

import com.example.spring_intro.model.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepo extends JpaRepository<Blog, Long> {
    Blog findByTitle(String blogName);
}
