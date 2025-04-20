package com.example.crud_intern.repo;

import com.example.crud_intern.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepo extends JpaRepository<Blog, Long> {
    Blog findByTitle(String blogName);
}
