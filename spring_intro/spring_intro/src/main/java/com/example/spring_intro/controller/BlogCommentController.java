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
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("blog/comment")
@RequiredArgsConstructor
public class BlogCommentController {

    private final BlogService blogService;
    private final UserCommentService userCommentService;
    private final RoleService roleService;
    private final CommentMapper commentMapper;

    @PostMapping("/create")
    public ResponseEntity<UserCommentDTO> createCommentBlog(@RequestParam(value = "user_id",defaultValue = "1") Long userId,
                                               @RequestParam(value="blog_id",defaultValue = "1")Long blogId,
                                               @RequestBody UserCommentDTO userCommentDTO) throws UnAuthorizedActionException, BlogNotFoundException, UserNotFoundException {
        if(!roleService.isAccessCreateComment(userId))
        {
            throw new UnAuthorizedActionException("You do not have permission to create comment this blog..!");
        }
        return ResponseEntity.ok(userCommentService.addUserComment(userId,blogId,userCommentDTO));
    }
    @GetMapping("/fetch/{comment_id}")
    public ResponseEntity<UserCommentDTO> fetchCommentBlogById(@PathVariable("comment_id") Long commentId) throws CommentNotFoundException {
        return ResponseEntity.ok(userCommentService.fetchCommentById(commentId));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCommentBlogById(@RequestParam("user_id") Long userId,
                                               @RequestParam("comment_id") Long commentId) throws UnAuthorizedActionException, CommentNotFoundException {
        if(!roleService.isAccessDeleteComment(userId))
        {
            throw new UnAuthorizedActionException("You do not have permission to delete comment this blog.");
        }
        userCommentService.deleteCommentById(commentId);
        return ResponseEntity.ok("Delete Comment Blog Successfully.");
    }
    @PutMapping("/update")
    public ResponseEntity<UserCommentDTO> updateCommentBlogById(@RequestParam("user_id") Long userId,
                                               @RequestParam("comment_id") Long commentId,
                                               @RequestBody UserCommentDTO userCommentDTO) throws UnAuthorizedActionException, CommentNotFoundException {
        if(!roleService.isAccessUpdateComment(userId))
        {
            throw new UnAuthorizedActionException("You do not have permission to update comment this blog.");
        }
        return ResponseEntity.ok(userCommentService.updateCommentById(commentId,userCommentDTO));
    }
}

