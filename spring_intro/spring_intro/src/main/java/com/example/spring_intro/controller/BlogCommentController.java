package com.example.spring_intro.controller;

import com.example.spring_intro.exception.BlogNotFoundException;
import com.example.spring_intro.exception.CommentNotFoundException;
import com.example.spring_intro.exception.UnAuthorizedActionException;
import com.example.spring_intro.exception.UserNotFoundException;
import com.example.spring_intro.model.dto.UserCommentDTO;
import com.example.spring_intro.model.mapper.CommentMapper;
import com.example.spring_intro.service.BlogService;
import com.example.spring_intro.service.RoleService;
import com.example.spring_intro.service.UserCommentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v3/blog/comment")
@RequiredArgsConstructor
public class BlogCommentController {

    private final BlogService blogService;
    private final UserCommentService userCommentService;
    private final RoleService roleService;
    private final CommentMapper commentMapper;

    @PostMapping("/create")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<UserCommentDTO> createCommentBlog(
                                               @RequestParam(value="blog_id")Long blogId,
                                               @RequestBody UserCommentDTO userCommentDTO) throws BlogNotFoundException, UserNotFoundException {
        if(!blogService.isExitBlogById(blogId)) {
            throw new BlogNotFoundException("Blog doesn't exit..!");
        }
        return ResponseEntity.ok(commentMapper
                .toUserCommentDTO(userCommentService
                .addUserComment(commentMapper.toUserComment(blogId,userCommentDTO))));
    }
    @GetMapping("/fetch/{comment_id}")
    public ResponseEntity<UserCommentDTO> fetchCommentBlogById(@PathVariable("comment_id") Long commentId) throws CommentNotFoundException {
        if(!userCommentService.isExitCommentById(commentId)) {
            throw new CommentNotFoundException("Comment doesn't exit..!");
        }
        return ResponseEntity.ok(commentMapper.toUserCommentDTO(userCommentService.fetchCommentById(commentId)));
    }
    @DeleteMapping("/delete")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<String> deleteCommentBlogById(@RequestParam("comment_id") Long commentId) throws CommentNotFoundException {
        if(!userCommentService.isExitCommentById(commentId)) {
            throw new CommentNotFoundException("Comment doesn't exit..!");
        }
        userCommentService.deleteCommentById(commentId);
        return ResponseEntity.ok("Delete Comment Blog Successfully.");
    }
    @PutMapping("/update")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<UserCommentDTO> updateCommentBlogById(
                                               @RequestParam("comment_id") Long commentId,
                                               @RequestBody UserCommentDTO userCommentDTO) throws CommentNotFoundException {
        if(!userCommentService.isExitCommentById(commentId)) {
            throw new CommentNotFoundException("Comment doesn't exit..!");
        }
        return ResponseEntity.ok(commentMapper
                .toUserCommentDTO(userCommentService
                .updateCommentById(commentId,userCommentDTO))
        );
    }
}

