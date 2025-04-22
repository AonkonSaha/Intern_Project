package com.example.spring_intro.controller;


import com.example.spring_intro.model.dto.BlogDTO;
import com.example.spring_intro.service.BlogService;
import com.example.spring_intro.service.UserCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/blog")
public class BlogController {

    @Autowired
    BlogService blogService;
    @Autowired
    UserCommentService userCommentService;
    @PostMapping("/save/{user_name}")
    public ResponseEntity<?> createBlog(@PathVariable("user_name") String userName, @RequestBody BlogDTO blog) {
//       System.out.println("Name: "+userName);
        return ResponseEntity.ok(blogService.addBlog(userName,blog));
    }
    @GetMapping("/fetch/{id}")
    public ResponseEntity<?> fetchBlog(@PathVariable("id") Long id) {

        return ResponseEntity.ok(blogService.fetchBlogById(id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable("id") Long id) {

        return ResponseEntity.ok(blogService.deleteBlogById(id));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBlog(@PathVariable("id") Long id,@RequestBody BlogDTO blog) {

        return ResponseEntity.ok(blogService.updateBlogById(id,blog));
    }


}
