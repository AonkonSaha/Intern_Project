package com.example.crud_intern.service;

import com.example.crud_intern.dto.BlogDTO;
import com.example.crud_intern.dto.BlogShowDTO;
import com.example.crud_intern.entity.Blog;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface BlogService {
    String addBlog(String userName,BlogDTO blog);

    BlogShowDTO fetchBlogById(Long id);

    String deleteBlogById(Long id);

    String updateBlogById(Long id,BlogDTO blogDTO);
}
