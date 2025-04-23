package com.example.spring_intro.model.mapping;

import com.example.spring_intro.model.dto.*;
import com.example.spring_intro.model.entity.Blog;
import com.example.spring_intro.model.entity.User;
import com.example.spring_intro.model.entity.UserComment;
import com.example.spring_intro.model.entity.UserRole;
import com.example.spring_intro.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CustomMapper {
    public User toUser(UserDTO userDTO)
    {
        User user=new User();
        user.setEmail(userDTO.getEmail());
        user.setUserName(userDTO.getUserName());
        user.setContact(userDTO.getContact());
        return user;
    }

    public UserDTO toUserDTO(User user) {
        UserDTO userDTO=new UserDTO();
        userDTO.setUserName(user.getUserName());
        userDTO.setEmail(user.getEmail());
        userDTO.setContact(user.getContact());
        return userDTO;
    }

    public Blog toBlog(BlogDTO blogDTO) {

        Blog blog=Blog.builder()
                .title(blogDTO.getTitle())
                .content(blogDTO.getContent())
                .build();
        return blog;
    }

    public UserComment toUserComment(UserCommentDTO userCommentDTO) {
        UserComment userComment=UserComment.builder()
                .content(userCommentDTO.getContent())
                .build();
        return userComment;
    }

    public BlogShowDTO toBlogShowDTO(Blog blog) {
        User user=blog.getAuthor();
        List<UserComment> userComments=blog.getUserComments();
        List<String>userCommentContents=new ArrayList<>();
        for(UserComment userComment:userComments)
        {
            userCommentContents.add(userComment.getContent());
        }

        BlogShowDTO blogShowDTO=BlogShowDTO.builder()
                .blogTitle(blog.getTitle())
                .content(blog.getContent())
                .createdAt(blog.getCreatedAt())
                .updatedAt(blog.getUpdatedAt())
                .author(user.getUserName())
                .comments(userCommentContents)
                .build();
        return blogShowDTO;

    }

    public UserCommentDTO toUserCommentDTO(UserComment userComment) {
        UserCommentDTO userCommentDTO=new UserCommentDTO();
        userCommentDTO.setContent(userComment.getContent());
        return userCommentDTO;
    }

    public UserRole toUserRole(RoleDTO roleDTO) {
        UserRole userRole=new UserRole();
        userRole.setRole(roleDTO.getRole());
        return userRole;
    }
}

