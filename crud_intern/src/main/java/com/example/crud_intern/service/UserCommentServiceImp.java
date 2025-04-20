package com.example.crud_intern.service;


import com.example.crud_intern.dto.UserCommentDTO;
import com.example.crud_intern.entity.Blog;
import com.example.crud_intern.entity.User;
import com.example.crud_intern.entity.UserComment;
import com.example.crud_intern.mapping.CustomMapper;
import com.example.crud_intern.repo.BlogRepo;
import com.example.crud_intern.repo.UserCommentRepo;
import com.example.crud_intern.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserCommentServiceImp implements UserCommentService {

    @Autowired
    CustomMapper customMapper;
    @Autowired
    UserRepo userRepo;
    @Autowired
    BlogRepo blogRepo;
    @Autowired
    UserCommentRepo userCommentRepo;
    @Override
    public String addUserComment(String blogName,UserCommentDTO userCommentDTO) {
        UserComment userComment = customMapper.toUserComment(userCommentDTO);
        Blog blog=blogRepo.findByTitle(blogName);
        User user=blog.getAuthors().getFirst();
        userComment.setUser(user);
        userComment.setBlog(blog);
        userComment.setBlogName(blogName);
        userComment.setAuthor(user.getUserName());
        userCommentRepo.save(userComment);
        return "Comment added successfully...!";
    }

    @Override
    public UserCommentDTO fetchCommentById(Long id) {
        UserComment userComment=userCommentRepo.findById(id).orElseThrow();
        UserCommentDTO userCommentDTO=customMapper.toUserCommentDTO(userComment);
        return userCommentDTO;
    }

    @Override
    public String deleteCommentById(Long id) {
        userCommentRepo.deleteById(id);
        return "Comment deleted successfully...";
    }

    @Override
    public String updateCommentById(Long id,UserCommentDTO userCommentDTO) {
        UserComment userComment=userCommentRepo.findById(id).orElseThrow();
        userComment.setContent(userCommentDTO.getContent());
        userCommentRepo.save(userComment);
        return "";
    }
}
