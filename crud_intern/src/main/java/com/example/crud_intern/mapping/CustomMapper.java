package com.example.crud_intern.mapping;

import com.example.crud_intern.dto.BlogDTO;
import com.example.crud_intern.dto.BlogShowDTO;
import com.example.crud_intern.dto.UserCommentDTO;
import com.example.crud_intern.dto.UserDTO;
import com.example.crud_intern.entity.Blog;
import com.example.crud_intern.entity.User;
import com.example.crud_intern.entity.UserComment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

        BlogShowDTO blogShowDTO=BlogShowDTO.builder()
                .blogTitle(blog.getTitle())
                .content(blog.getContent())
                .createdAt(blog.getCreatedAt())
                .updatedAt(blog.getUpdatedAt())
                .build();
        List<User>users=blog.getAuthors();
        List<UserComment> userComments=blog.getUserComments();

        List<String>authorNames=new ArrayList<>();
        List<String>userCommentContents=new ArrayList<>();
        for(User user:users)
        {
            authorNames.add(user.getUserName());
        }
        for(UserComment userComment:userComments)
        {
            userCommentContents.add(userComment.getContent());
        }
        blogShowDTO.setAuthors(authorNames);
        blogShowDTO.setComments(userCommentContents);
        return blogShowDTO;

    }

    public UserCommentDTO toUserCommentDTO(UserComment userComment) {
        UserCommentDTO userCommentDTO=new UserCommentDTO();
        userCommentDTO.setContent(userComment.getContent());
        return userCommentDTO;
    }
}
