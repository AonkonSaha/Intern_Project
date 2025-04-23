package com.example.spring_intro.controller;

import com.example.spring_intro.model.dto.UserCommentDTO;
import com.example.spring_intro.service.BlogService;
import com.example.spring_intro.service.RoleService;
import com.example.spring_intro.service.UserCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/api/blog/comment")
@RequiredArgsConstructor
public class BlogCommentController {

    private final BlogService blogService;
    private final UserCommentService userCommentService;
    private final RoleService roleService;

    @PostMapping("/{user_id}/{blog_id}")
    public ResponseEntity<?> createCommentBlog(@PathVariable("user_id")Long userId,
                                               @PathVariable("blog_id")Long blogId,
                                               @RequestBody UserCommentDTO userCommentDTO) throws AccessDeniedException {
        if(roleService.isAccessCreateComment(userId))
        {
            throw new AccessDeniedException("You do not have permission to create comment this blog.");

        }
        return ResponseEntity.ok(userCommentService.addUserComment(userId,blogId,userCommentDTO));
    }
    @GetMapping("/fetch/{user_id}/{blog_id}")
    public ResponseEntity<?> fetchCommentBlog(@PathVariable("user_id") Long userId,
                                              @PathVariable("blog_id") Long blogId) {
        return ResponseEntity.ok(userCommentService.fetchCommentById(userId,blogId));
    }
    @DeleteMapping("/delete/{user_id}/{blog_id}")
    public ResponseEntity<?> deleteCommentBlog(@PathVariable("user_id") Long userId,
                                               @PathVariable("blog_id") Long blogId) throws AccessDeniedException {
        if(roleService.isAccessDeleteComment(userId))
        {
            throw new AccessDeniedException("You do not have permission to delete comment this blog.");
        }
        userCommentService.deleteCommentById(userId,blogId);
        return ResponseEntity.ok("Delete Comment Blog Successfully.");
    }
    @PutMapping("/update/{user_id}/{blog_id}/{comment_id}")
    public ResponseEntity<?> updateCommentBlog(@PathVariable("user_id") Long userId,
                                               @PathVariable("blog_id") Long blogId,
                                               @PathVariable("comment_id") Long commentId,
                                               @RequestBody UserCommentDTO userCommentDTO) throws AccessDeniedException {
        if(roleService.isAccessUpdateComment(userId))
        {
            throw new AccessDeniedException("You do not have permission to update comment this blog.");

        }
        return ResponseEntity.ok(userCommentService.updateCommentById(
                userId,
                blogId,
                commentId,
                userCommentDTO));
    }
}

