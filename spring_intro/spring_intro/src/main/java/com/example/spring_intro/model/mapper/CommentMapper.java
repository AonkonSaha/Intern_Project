package com.example.spring_intro.model.mapper;

import com.example.spring_intro.model.dto.UserCommentDTO;
import com.example.spring_intro.model.entity.UserComment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {


    public UserComment toUserComment(UserCommentDTO userCommentDTO) {
        UserComment userComment=UserComment.builder()
                .content(userCommentDTO.getContent())
                .build();
        return userComment;
    }


    public UserCommentDTO toUserCommentDTO(UserComment userComment) {
        UserCommentDTO userCommentDTO=new UserCommentDTO();
        userCommentDTO.setContent(userComment.getContent());
        return userCommentDTO;
    }

}
