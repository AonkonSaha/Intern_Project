package com.example.spring_intro.model.mapper;

import com.example.spring_intro.model.dto.BlogDTO;
import com.example.spring_intro.model.dto.BlogShowDTO;
import com.example.spring_intro.model.entity.Blog;
import com.example.spring_intro.model.entity.User;
import com.example.spring_intro.model.entity.UserComment;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BlogMapper {
    public Blog toBlog(BlogDTO blogDTO) {

        Blog blog=Blog.builder()
                .title(blogDTO.getTitle())
                .content(blogDTO.getContent())
                .build();
        return blog;
    }
    public BlogDTO toBlogDTO(Blog blog) {
        BlogDTO blogDTO=new BlogDTO();
        blogDTO.setId(blog.getId());
        blogDTO.setTitle(blog.getTitle());
        blogDTO.setContent(blog.getContent());
        blogDTO.setCreatedAt(blog.getCreatedAt().toInstant(ZoneOffset.UTC).toEpochMilli());
        blogDTO.setUpdatedAt(blog.getUpdatedAt().toInstant(ZoneOffset.UTC).toEpochMilli());
        return blogDTO;
    }
}
