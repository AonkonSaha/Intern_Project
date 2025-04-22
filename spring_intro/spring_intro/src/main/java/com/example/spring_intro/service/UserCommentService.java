package com.example.spring_intro.service;

import com.example.spring_intro.model.dto.UserCommentDTO;
import com.example.spring_intro.model.entity.Blog;
import com.example.spring_intro.model.entity.User;
import com.example.spring_intro.model.entity.UserComment;
import com.example.spring_intro.model.mapping.CustomMapper;
import com.example.spring_intro.repository.BlogRepo;
import com.example.spring_intro.repository.UserCommentRepo;
import com.example.spring_intro.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCommentService {

    @Autowired
    CustomMapper customMapper;
    @Autowired
    UserRepo userRepo;
    @Autowired
    BlogRepo blogRepo;
    @Autowired
    UserCommentRepo userCommentRepo;
    public String addUserComment(String blogName, UserCommentDTO userCommentDTO) {
        UserComment userComment = customMapper.toUserComment(userCommentDTO);
        Blog blog=blogRepo.findByTitle(blogName);
        User user=blog.getAuthor();
        userComment.setUser(user);
        userComment.setBlog(blog);
        userComment.setBlogName(blogName);
        userComment.setAuthor(user.getUserName());
        userCommentRepo.save(userComment);
        return "Comment added successfully...!";
    }

    public UserCommentDTO fetchCommentById(Long id) {
        UserComment userComment=userCommentRepo.findById(id).orElseThrow();
        UserCommentDTO userCommentDTO=customMapper.toUserCommentDTO(userComment);
        return userCommentDTO;
    }

    public String deleteCommentById(Long id) {
        userCommentRepo.deleteById(id);
        return "Comment deleted successfully...";
    }


    public String updateCommentById(Long id,UserCommentDTO userCommentDTO) {
        UserComment userComment=userCommentRepo.findById(id).orElseThrow();
        userComment.setContent(userCommentDTO.getContent());
        userCommentRepo.save(userComment);
        return "";
    }


}
