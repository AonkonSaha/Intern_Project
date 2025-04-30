package com.example.spring_intro.service;

import com.example.spring_intro.exception.BlogNotFoundException;
import com.example.spring_intro.exception.CommentNotFoundException;
import com.example.spring_intro.exception.UserNotFoundException;
import com.example.spring_intro.model.dto.UserCommentDTO;
import com.example.spring_intro.model.entity.Blog;
import com.example.spring_intro.model.entity.User;
import com.example.spring_intro.model.entity.UserComment;
import com.example.spring_intro.model.mapper.CommentMapper;
import com.example.spring_intro.repository.BlogRepo;
import com.example.spring_intro.repository.UserCommentRepo;
import com.example.spring_intro.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserCommentService {

    private final UserRepo userRepo;
    private final BlogRepo blogRepo;
    private final UserCommentRepo userCommentRepo;
    private final CommentMapper commentMapper;

    public UserComment addUserComment(UserComment userComment){
        userCommentRepo.save(userComment);
        return userComment;
    }
    public UserComment fetchCommentById(Long id) throws CommentNotFoundException {
         Optional<UserComment> userComment=userCommentRepo.findById(id);
         if(userComment.isEmpty())
         {
             throw new CommentNotFoundException("Comment doesn't exit..!");
         }
        return userComment.get();
    }

    public void deleteCommentById(Long commentId) throws CommentNotFoundException {

        Optional<UserComment> userComment = userCommentRepo.findById(commentId);
        if (userComment.isEmpty()) {
            throw new CommentNotFoundException("Comment doesn't exit..!");
        }
        userCommentRepo.deleteById(commentId);
    }


    public UserComment updateCommentById(Long commentId, UserCommentDTO userCommentDTO) throws CommentNotFoundException {
        Optional<UserComment> userComment = userCommentRepo.findById(commentId);
        if (userComment.isEmpty()) {
            throw new CommentNotFoundException("Comment doesn't exit..!");
        }
        userComment.get().setContent(userCommentDTO.getContent());
        userCommentRepo.save(userComment.get());
        return userComment.get();
    }

    public UserComment findByAuthorName(String userName) throws CommentNotFoundException {
        Optional<UserComment> userComment= Optional.ofNullable(userCommentRepo.findByAuthor(userName));
        if(userComment.isEmpty())
        {
            throw new CommentNotFoundException("UserComment doesn't exit..");
        }
        return userComment.get();
    }
}
