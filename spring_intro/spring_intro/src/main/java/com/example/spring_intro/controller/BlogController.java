package com.example.spring_intro.controller;


import com.example.spring_intro.exception.BlogNotFoundException;
import com.example.spring_intro.exception.CommentNotFoundException;
import com.example.spring_intro.exception.UserNotFoundException;
import com.example.spring_intro.model.dto.BlogDTO;
import com.example.spring_intro.model.mapper.BlogMapper;
import com.example.spring_intro.service.BlogService;
import com.example.spring_intro.service.RoleService;
import com.example.spring_intro.service.UserCommentService;
import com.example.spring_intro.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/save")
    public ResponseEntity<BlogDTO> createBlog(@RequestBody BlogDTO blogDTO) throws UserNotFoundException, CommentNotFoundException {
        return ResponseEntity.ok(blogMapper.toBlogDTO(blogService.addBlog(blogMapper.toBlog(blogDTO))));
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<BlogDTO> fetchBlogById(@PathVariable("id") Long id) throws BlogNotFoundException {
        if(!blogService.isExitBlogById(id)) {
            throw new BlogNotFoundException("Blog doesn't exit..!");
        }
        return ResponseEntity.ok(blogMapper.toBlogDTO(blogService.fetchBlogById(id)));
    }

    @DeleteMapping("/delete")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<String> deleteBlogById(@RequestParam("blog_id") Long blogId) throws BlogNotFoundException {
        if(!blogService.isExitBlogById(blogId)) {
            throw new BlogNotFoundException("Blog doesn't exit..!");
        }
        blogService.deleteBlogById(blogId);
        return ResponseEntity.ok("Deleted blog successfully.");
    }
    @PutMapping("/update")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<BlogDTO> updateBlogById(
                                        @RequestParam("blog_id") Long blogId,
                                        @RequestBody BlogDTO blogDTO) throws BlogNotFoundException, UserNotFoundException, CommentNotFoundException {
        if(!blogService.isExitBlogById(blogId)) {
            throw new BlogNotFoundException("Blog doesn't exit..!");
        }
        return ResponseEntity.ok(blogMapper
                .toBlogDTO(blogService.updateBlogById(blogId,blogMapper.toBlog(blogDTO)))
        );
    }


}
