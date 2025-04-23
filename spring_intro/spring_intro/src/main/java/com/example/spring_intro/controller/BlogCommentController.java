package com.example.spring_intro.controller;

import com.example.spring_intro.model.dto.UserCommentDTO;
import com.example.spring_intro.service.BlogService;
import com.example.spring_intro.service.UserCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/blog/comment")
@RequiredArgsConstructor
public class BlogCommentController {

    private final BlogService blogService;
    private final UserCommentService userCommentService;

    @PostMapping("/{blog_post_name}")
    public ResponseEntity<?> createCommentBlog(@PathVariable("blog_post_name") String blogName, @RequestBody UserCommentDTO userCommentDTO) {
        return ResponseEntity.ok(userCommentService.addUserComment (blogName,userCommentDTO));
    }
    @GetMapping("/fetch/{id}")
    public ResponseEntity<?> fetchCommentBlog(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userCommentService.fetchCommentById(id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCommentBlog(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userCommentService.deleteCommentById(id));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> deleteCommentBlog(@PathVariable("id") Long id, @RequestBody UserCommentDTO userCommentDTO) {
        return ResponseEntity.ok(userCommentService.updateCommentById(id,userCommentDTO));
    }
}

