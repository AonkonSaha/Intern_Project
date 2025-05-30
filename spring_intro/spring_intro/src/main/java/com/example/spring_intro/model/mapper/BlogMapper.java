package com.example.spring_intro.model.mapper;

import com.example.spring_intro.exception.CommentNotFoundException;
import com.example.spring_intro.exception.UserNotFoundException;
import com.example.spring_intro.model.dto.BlogDTO;
import com.example.spring_intro.model.dto.BlogShowDTO;
import com.example.spring_intro.model.entity.Blog;
import com.example.spring_intro.model.entity.User;
import com.example.spring_intro.model.entity.UserComment;
import com.example.spring_intro.repository.UserCommentRepo;
import com.example.spring_intro.service.UserCommentService;
import com.example.spring_intro.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BlogMapper {
    private final UserService userService;
    private final UserCommentService userCommentService;
    public Blog toBlog(BlogDTO blogDTO) throws UserNotFoundException, CommentNotFoundException {
        String userName=SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userService.getUserByName(userName);
        UserComment userComment=userCommentService.findByAuthorName(user.getUserName());
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
                .authorUserId(blog.getAuthor().getId())
                .content(blog.getContent())
                .createdAt(blog.getCreatedAt().toInstant(ZoneOffset.UTC).toEpochMilli())
                .updatedAt(blog.getCreatedAt().toInstant(ZoneOffset.UTC).toEpochMilli())
                .build();

    }
}
