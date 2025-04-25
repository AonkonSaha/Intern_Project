package com.example.spring_intro.model.mapper;

import com.example.spring_intro.model.dto.UserCommentDTO;
import com.example.spring_intro.model.entity.Blog;
import com.example.spring_intro.model.entity.User;
import com.example.spring_intro.model.entity.UserComment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {


    public UserComment toUserComment(Blog blog, User user, UserCommentDTO userCommentDTO) {
        return UserComment.builder()
                .content(userCommentDTO.getContent())
                .blog(blog)
                .user(user)
                .blogName(blog.getTitle())
                .author(user.getUserName())
                .build();
    }


    public UserCommentDTO toUserCommentDTO(UserComment userComment) {
        return UserCommentDTO.builder()
                .content(userComment.getContent())
                .author(userComment.getUser().getUserName())
                .blogName(userComment.getBlog().getTitle())
                .build();
    }

}
