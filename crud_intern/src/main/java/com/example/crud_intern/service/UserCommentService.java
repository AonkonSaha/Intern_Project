package com.example.crud_intern.service;

import com.example.crud_intern.dto.UserCommentDTO;
import com.example.crud_intern.entity.UserComment;
import org.springframework.stereotype.Service;

@Service
public interface UserCommentService {

    String addUserComment(String blogName,UserCommentDTO userCommentDTO);

    UserCommentDTO fetchCommentById(Long id);

    String deleteCommentById(Long id);

    String updateCommentById(Long id,UserCommentDTO userCommentDTO);
}
