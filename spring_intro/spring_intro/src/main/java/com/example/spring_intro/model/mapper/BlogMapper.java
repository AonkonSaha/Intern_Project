package com.example.spring_intro.model.mapper;

import com.example.spring_intro.model.dto.BlogDTO;
import com.example.spring_intro.model.dto.BlogShowDTO;
import com.example.spring_intro.model.entity.Blog;
import com.example.spring_intro.model.entity.User;
import com.example.spring_intro.model.entity.UserComment;
import com.example.spring_intro.repository.UserCommentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BlogMapper {
    private final UserCommentRepo userCommentRepo;
    public Blog toBlog(User user,BlogDTO blogDTO) {
        UserComment userComment=userCommentRepo.findByAuthor(user.getUserName());
        return Blog.builder()
                .title(blogDTO.getTitle())
                .content(blogDTO.getContent())
                .author(user)
                .userComments(userComment != null ? List.of(userComment) : new ArrayList<>())
                .build();
    }
    public BlogDTO toBlogDTO(Blog blog) {
        return BlogDTO.builder()
                .id(blog.getId())
                .title(blog.getTitle())
                .authorUserId(blog.getId())
                .content(blog.getContent())
                .createdAt(blog.getCreatedAt().toInstant(ZoneOffset.UTC).toEpochMilli())
                .updatedAt(blog.getCreatedAt().toInstant(ZoneOffset.UTC).toEpochMilli())
                .build();

    }
}
