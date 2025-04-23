package com.example.spring_intro.controller;


import com.example.spring_intro.model.dto.BlogDTO;
import com.example.spring_intro.model.mapper.BlogMapper;
import com.example.spring_intro.service.BlogService;
import com.example.spring_intro.service.RoleService;
import com.example.spring_intro.service.UserCommentService;
import com.example.spring_intro.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/api/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;
    private final UserCommentService userCommentService;
    private final RoleService roleService;

   @PostMapping("/save")
    public ResponseEntity<?> createBlog(@RequestBody BlogDTO blogDTO) throws AccessDeniedException {
       if(!roleService.isAccessCreateBlog(blogDTO.getAuthorUserId()))
       {
           System.out.println("Author Id: "+blogDTO.getAuthorUserId());
           throw new AccessDeniedException("You do not have permission to create this blog.");
       }
        return ResponseEntity.ok(blogService.addBlog(blogDTO));
    }
    @GetMapping("/fetch/{id}")
    public ResponseEntity<?> fetchBlog(@PathVariable("id") Long id) {

        return ResponseEntity.ok(blogService.fetchBlogById(id));
    }
    @DeleteMapping("/delete/{blog_id}/{user_id}")
    public ResponseEntity<String> deleteBlog(@PathVariable("blog_id") Long blogId, @PathVariable("user_id") Long userId) throws AccessDeniedException {

        if(!roleService.isAccessDeleteBLog(userId))
        {
            throw new AccessDeniedException("You do not have permission to create this blog.");
        }
        blogService.deleteBlogById(blogId,userId);
        return ResponseEntity.ok("Deleted blog successfully.");
    }
    @PutMapping("/update/{blog_id}/{user_id}")
    public ResponseEntity<?> updateBlog(@PathVariable("blog_id") Long blogId,
                                        @PathVariable("user_id") Long userId,
                                        @RequestBody BlogDTO blogDTO) throws AccessDeniedException {
        if(!roleService.isAccessUpdateBlog(userId))
        {
            throw new AccessDeniedException("You do not have permission to create this blog.");
        }
        return ResponseEntity.ok(blogService.updateBlogById(blogId,userId,blogDTO));
    }


}
