package com.example.spring_intro.service;

import com.example.spring_intro.model.dto.UserCommentDTO;
import com.example.spring_intro.model.entity.Blog;
import com.example.spring_intro.model.entity.User;
import com.example.spring_intro.model.entity.UserComment;
import com.example.spring_intro.model.mapping.CustomMapper;
import com.example.spring_intro.repository.BlogRepo;
import com.example.spring_intro.repository.UserCommentRepo;
import com.example.spring_intro.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCommentService {

    private final CustomMapper customMapper;
    private final UserRepo userRepo;
    private final BlogRepo blogRepo;
    private final UserCommentRepo userCommentRepo;

    public String addUserComment(String blogName, UserCommentDTO userCommentDTO) {
        UserComment userComment = customMapper.toUserComment(userCommentDTO);
        Blog blog=blogRepo.findByTitle(blogName);
        User user=blog.getAuthor();
        boolean isAuthorized = user.getUserRole().stream()
                .anyMatch(role -> role.getRole().equalsIgnoreCase("ADMIN") || role.getRole().equalsIgnoreCase("MODERATOR")
                        || role.getRole().equalsIgnoreCase("Author") || role.getRole().equalsIgnoreCase("USER"));
        if (!isAuthorized) {
            return "You aren't authorized to write comment...!";
        }
        userComment.setUser(user);
        userComment.setBlog(blog);
        userComment.setBlogName(blogName);
        userComment.setAuthor(user.getUserName());
        userCommentRepo.save(userComment);
        return "Comment added successfully...!";
    }

    public ResponseEntity<?> fetchCommentById(Long id) {
        UserComment userComment=userCommentRepo.findById(id).orElseThrow();
        Blog blog=blogRepo.findByTitle(userComment.getBlogName() );
        User user=blog.getAuthor();
        boolean isAuthorized = user.getUserRole().stream()
                .anyMatch(role -> role.getRole().equalsIgnoreCase("ADMIN") || role.getRole().equalsIgnoreCase("MODERATOR")
                        || role.getRole().equalsIgnoreCase("Author"));
        if (!isAuthorized) {
            return ResponseEntity.ok("You aren't authorized to see the comment...!");
        }

        UserCommentDTO userCommentDTO=customMapper.toUserCommentDTO(userComment);
        return ResponseEntity.ok(userCommentDTO);
    }

    public ResponseEntity<?> deleteCommentById(Long id) {

        UserComment userComment=userCommentRepo.findById(id).orElseThrow();
        Blog blog=blogRepo.findByTitle(userComment.getBlogName());
        User user=blog.getAuthor();
        boolean isAuthorized = user.getUserRole().stream()
                .anyMatch(role -> role.getRole().equalsIgnoreCase("ADMIN") || role.getRole().equalsIgnoreCase("MODERATOR")
                        || role.getRole().equalsIgnoreCase("Author"));
        if (!isAuthorized) {
            return ResponseEntity.ok("You aren't authorized to delete the comment...!");
        }
        userCommentRepo.deleteById(id);
        return ResponseEntity.ok("Comment deleted successfully...");
    }


    public ResponseEntity<?> updateCommentById(Long id,UserCommentDTO userCommentDTO) {
        UserComment userComment=userCommentRepo.findById(id).orElseThrow();
        Blog blog=blogRepo.findByTitle(userComment.getBlogName());
        User user=blog.getAuthor();
        boolean isAuthorized = user.getUserRole().stream()
                .anyMatch(role -> role.getRole().equalsIgnoreCase("ADMIN") || role.getRole().equalsIgnoreCase("MODERATOR")
                        || role.getRole().equalsIgnoreCase("Author"));
        if (!isAuthorized) {
            return ResponseEntity.ok("You aren't authorized to create a blog...!");
        }
        userComment.setContent(userCommentDTO.getContent());
        userCommentRepo.save(userComment);
        return ResponseEntity.ok("Comment Update Successfully...");
    }


}
