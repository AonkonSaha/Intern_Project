package com.example.crud_intern.controller;


import com.example.crud_intern.dto.BlogDTO;
import com.example.crud_intern.dto.UserCommentDTO;
import com.example.crud_intern.entity.Blog;
import com.example.crud_intern.service.BlogService;
import com.example.crud_intern.service.UserCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BlogController {

    @Autowired
    BlogService blogService;
    @Autowired
    UserCommentService userCommentService;
    @PostMapping("/blog/save/{user_name}")
    public ResponseEntity<?> createBlog(@PathVariable("user_name") String userName, @RequestBody BlogDTO blog) {
//       System.out.println("Name: "+userName);
        return ResponseEntity.ok(blogService.addBlog(userName,blog));
    }
    @GetMapping("blog/fetch/{id}")
    public ResponseEntity<?> fetchBlog(@PathVariable("id") Long id) {

        return ResponseEntity.ok(blogService.fetchBlogById(id));
    }
    @DeleteMapping("blog/delete/{id")
    public ResponseEntity<?> deleteBlog(@PathVariable("id") Long id) {

        return ResponseEntity.ok(blogService.deleteBlogById(id));
    }
    @PutMapping("blog/update/{id}")
    public ResponseEntity<?> updateBlog(@PathVariable("id") Long id,@RequestBody BlogDTO blog) {

        return ResponseEntity.ok(blogService.updateBlogById(id,blog));
    }


}
