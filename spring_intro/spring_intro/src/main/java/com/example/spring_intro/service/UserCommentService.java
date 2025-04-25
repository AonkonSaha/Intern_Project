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

    public UserCommentDTO addUserComment(Long authorId, Long blogId, UserCommentDTO userCommentDTO) throws BlogNotFoundException, UserNotFoundException {
        Optional<Blog> blog = blogRepo.findById(blogId);
        Optional<User> user = userRepo.findById(authorId);
        if (blog.isEmpty()) {
            throw new BlogNotFoundException("Blog doesn't exit..!");
        }
        if (user.isEmpty()) {
            throw new UserNotFoundException("User doesn't exit..!");
        }
        UserComment userComment=commentMapper.toUserComment(blog.get(),user.get(),userCommentDTO);
        userCommentRepo.save(userComment);
        return commentMapper.toUserCommentDTO(userComment);
    }

    public UserCommentDTO fetchCommentById(Long id) throws CommentNotFoundException {
         Optional<UserComment> userComment=userCommentRepo.findById(id);
         if(userComment.isEmpty())
         {
             throw new CommentNotFoundException("Comment doesn't exit..!");
         }
        return commentMapper.toUserCommentDTO(userComment.get());
    }

    public void deleteCommentById(Long commentId) throws CommentNotFoundException {

        Optional<UserComment> userComment = userCommentRepo.findById(commentId);
        if (userComment.isEmpty()) {
            throw new CommentNotFoundException("Comment doesn't exit..!");
        }
        userCommentRepo.deleteById(commentId);
    }


    public UserCommentDTO updateCommentById(Long commentId, UserCommentDTO userCommentDTO) throws CommentNotFoundException {
        Optional<UserComment> userComment = userCommentRepo.findById(commentId);
        if (userComment.isEmpty()) {
            throw new CommentNotFoundException("Comment doesn't exit..!");
        }
        userComment.get().setContent(userCommentDTO.getContent());
        userCommentRepo.save(userComment.get());
        return commentMapper.toUserCommentDTO(userComment.get());
    }
}
