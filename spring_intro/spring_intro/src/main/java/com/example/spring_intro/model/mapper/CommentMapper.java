package com.example.spring_intro.model.mapper;

import com.example.spring_intro.exception.BlogNotFoundException;
import com.example.spring_intro.exception.UserNotFoundException;
import com.example.spring_intro.model.dto.UserCommentDTO;
import com.example.spring_intro.model.entity.Blog;
import com.example.spring_intro.model.entity.User;
import com.example.spring_intro.model.entity.UserComment;
import com.example.spring_intro.repository.BlogRepo;
import com.example.spring_intro.repository.UserRepo;
import com.example.spring_intro.service.BlogService;
import com.example.spring_intro.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CommentMapper {
    private final BlogRepo blogRepo;
    private final UserRepo userRepo;
    public UserComment toUserComment(Long blogId, Long userId, UserCommentDTO userCommentDTO) throws UserNotFoundException, BlogNotFoundException {

        Optional<Blog> blog=blogRepo.findById(blogId);
        Optional<User> user=userRepo.findById(userId);
        if(blog.isEmpty())
        {
            throw new BlogNotFoundException("Blog doesn't exit..!!");
        }
        if(user.isEmpty())
        {
            throw new UserNotFoundException("User doesn't exit..!!");
        }
        return UserComment.builder()
                .content(userCommentDTO.getContent())
                .blog(blog.get())
                .user(user.get())
                .blogName(blog.get().getTitle())
                .author(user.get().getUserName())
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
