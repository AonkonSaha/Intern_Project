package com.example.spring_intro.controller;


import com.example.spring_intro.exception.BlogNotFoundException;
import com.example.spring_intro.exception.UserNotFoundException;
import com.example.spring_intro.model.dto.BlogDTO;
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

@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;
    private final UserCommentService userCommentService;
    private final RoleService roleService;
    @GetMapping("/")
    public ResponseEntity<String> blogCheck() {
       return ResponseEntity.ok("Blog is here....!");

    }
   @PostMapping("/save")
    public ResponseEntity<BlogDTO> createBlog(@RequestBody BlogDTO blogDTO) throws AccessDeniedException, UserNotFoundException {
       if(!roleService.isAccessCreateBlog(blogDTO.getAuthorUserId()))
       {
           throw new AccessDeniedException("You do not have permission to create this blog.");
       }
        return ResponseEntity.ok(blogService.addBlog(blogDTO));
    }
    @GetMapping("/fetch/{id}")
    public ResponseEntity<BlogDTO> fetchBlogById(@PathVariable("id") Long id) throws BlogNotFoundException {

        return ResponseEntity.ok(blogService.fetchBlogById(id));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBlogById(@RequestParam("user_id") Long userId,
                                             @RequestParam("blog_id") Long blogId) throws AccessDeniedException, BlogNotFoundException {

        if(!roleService.isAccessDeleteBLog(userId))
        {
            throw new AccessDeniedException("You do not have permission to delete this blog.");
        }
        blogService.deleteBlogById(blogId);
        return ResponseEntity.ok("Deleted blog successfully.");
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateBlogById(@RequestParam("user_id") Long userId,
                                        @RequestParam("blog_id") Long blogId,
                                        @RequestBody BlogDTO blogDTO) throws AccessDeniedException, BlogNotFoundException {
        if(!roleService.isAccessUpdateBlog(userId))
        {
            throw new AccessDeniedException("You do not have permission to update this blog.");
        }
        return ResponseEntity.ok(blogService.updateBlogById(blogId,blogDTO));
    }


}
