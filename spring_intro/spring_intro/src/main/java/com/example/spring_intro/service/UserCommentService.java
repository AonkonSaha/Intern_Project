package com.example.spring_intro.service;

import com.example.spring_intro.model.dto.UserCommentDTO;
import com.example.spring_intro.model.entity.Blog;
import com.example.spring_intro.model.entity.User;
import com.example.spring_intro.model.entity.UserComment;
import com.example.spring_intro.model.mapper.CommentMapper;
import com.example.spring_intro.repository.BlogRepo;
import com.example.spring_intro.repository.UserCommentRepo;
import com.example.spring_intro.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
@RequiredArgsConstructor
public class UserCommentService {

    private final UserRepo userRepo;
    private final BlogRepo blogRepo;
    private final UserCommentRepo userCommentRepo;
    private final CommentMapper commentMapper;

    public UserCommentDTO addUserComment(Long authorId, Long blogId, UserCommentDTO userCommentDTO) {
        Optional<Blog> blog = blogRepo.findById(blogId);
        Optional<User> user = userRepo.findById(authorId);
        if (blog.isEmpty() || user.isEmpty()) {
            return null;
        }
        UserComment userComment = commentMapper.toUserComment(userCommentDTO);
        userComment.setUser(user.get());
        userComment.setBlog(blog.get());
        userComment.setBlogName(blog.get().getTitle());
        userComment.setAuthor(user.get().getUserName());
        userCommentRepo.save(userComment);
        return commentMapper.toUserCommentDTO(userComment);
    }

    public List<UserCommentDTO> fetchCommentById(Long authorId, Long blogId) {
        Optional<Blog> blog = blogRepo.findById(blogId);
        if (blog.isEmpty()) {
            return null;
        }
        List<UserComment> userComments = blog.get().getUserComments();
        List<UserCommentDTO> userCommentDTOS = new ArrayList<>();
        for (UserComment userComment : userComments) {
            userCommentDTOS.add(commentMapper.toUserCommentDTO(userComment));
        }
        return userCommentDTOS;
    }

    public void deleteCommentById(Long authorId, Long blogId) {

        Optional<Blog> blog = blogRepo.findById(blogId);
        if (blog.isEmpty()) {
            System.out.println("Blog doesnot exist...");
            return;
        }
        List<UserComment> userComments = blog.get().getUserComments();
        for (UserComment userComment : userComments) {
            userCommentRepo.delete(userComment);
        }
    }


    public UserCommentDTO updateCommentById(Long userId, Long blogId, Long commentId, UserCommentDTO userCommentDTO) {

        Optional<UserComment> userComment = userCommentRepo.findById(commentId);
        Optional<Blog> blog = blogRepo.findById(blogId);
        Optional<User> user = userRepo.findById(userId);
        if (userComment.isEmpty() || blog.isEmpty() || user.isEmpty()) {
            return null;
        }
        userComment.get().setContent(userCommentDTO.getContent());
        userCommentRepo.save(userComment.get());
        return commentMapper.toUserCommentDTO(userComment.get());


    }
}
