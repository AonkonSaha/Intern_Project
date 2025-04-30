package com.example.spring_intro.controller;


import com.example.spring_intro.exception.BlogNotFoundException;
import com.example.spring_intro.exception.CommentNotFoundException;
import com.example.spring_intro.exception.UserNotFoundException;
import com.example.spring_intro.model.dto.BlogDTO;
import com.example.spring_intro.model.entity.User;
import com.example.spring_intro.model.mapper.BlogMapper;
import com.example.spring_intro.service.BlogService;
import com.example.spring_intro.service.RoleService;
import com.example.spring_intro.service.UserCommentService;
import com.example.spring_intro.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.Optional;

@RestController
@RequestMapping("api/v2/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;
    private final UserCommentService userCommentService;
    private final RoleService roleService;
    private final BlogMapper blogMapper;
    private final UserService userService;
    @GetMapping("/")
    public ResponseEntity<String> blogCheck() {
       return ResponseEntity.ok("Blog is here....!");

    }
   @PostMapping("/save")
    public ResponseEntity<BlogDTO> createBlog(@RequestBody BlogDTO blogDTO) throws UserNotFoundException, CommentNotFoundException {
        return ResponseEntity.ok(blogMapper.toBlogDTO(blogService.addBlog(blogMapper.toBlog(blogDTO))));
    }
    @GetMapping("/fetch/{id}")
    public ResponseEntity<BlogDTO> fetchBlogById(@PathVariable("id") Long id) throws BlogNotFoundException {

        return ResponseEntity.ok(blogMapper.toBlogDTO(blogService.fetchBlogById(id)));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBlogById(@RequestParam("user_id") Long userId,
                                             @RequestParam("blog_id") Long blogId) throws BlogNotFoundException {
        blogService.deleteBlogById(blogId);
        return ResponseEntity.ok("Deleted blog successfully.");
    }
    @PutMapping("/update")
    public ResponseEntity<BlogDTO> updateBlogById(@RequestParam("user_id") Long userId,
                                        @RequestParam("blog_id") Long blogId,
                                        @RequestBody BlogDTO blogDTO) throws BlogNotFoundException, UserNotFoundException, CommentNotFoundException {

        return ResponseEntity.ok(blogMapper
                .toBlogDTO(blogService.updateBlogById(blogId,blogMapper.toBlog(blogDTO)))
        );
    }


}
