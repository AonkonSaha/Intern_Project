package com.example.spring_intro.service;

import com.example.spring_intro.model.dto.BlogDTO;
import com.example.spring_intro.model.dto.BlogShowDTO;
import com.example.spring_intro.model.entity.Blog;
import com.example.spring_intro.model.entity.User;
import com.example.spring_intro.model.entity.UserComment;
import com.example.spring_intro.model.mapping.CustomMapper;
import com.example.spring_intro.repository.BlogRepo;
import com.example.spring_intro.repository.UserCommentRepo;
import com.example.spring_intro.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService{

    private final CustomMapper customMapper;
    private final UserRepo userRepo;
    private final BlogRepo blogRepo;
    private final UserCommentRepo userCommentRepo;

    public String addBlog(String userName, BlogDTO blogDTO) {
        try
        {
            User user=userRepo.findByUserName(userName);
            boolean isAuthorized = user.getUserRole().stream()
                    .anyMatch(role -> role.getRole().equalsIgnoreCase("ADMIN") || role.getRole().equalsIgnoreCase("MODERATOR")
                    || role.getRole().equalsIgnoreCase("Author"));
            if (!isAuthorized) {
                return "You aren't authorized to create a blog...!";
            }

            Blog blog = customMapper.toBlog(blogDTO);


            UserComment userComment=userCommentRepo.findByAuthor(userName);
            blog.setAuthor(user);
            blog.setUserComments(userComment != null ? List.of(userComment) : new ArrayList<>());
            blogRepo.save(blog);
            return "Blog Create Successfully...!!";
        }catch (Exception e)
        {
            e.printStackTrace();
            return "error: "+e.getMessage();
        }


    }


    public ResponseEntity<?> fetchBlogById(Long id) {

        Blog blog=blogRepo.findById(id).orElseThrow();
        User user=blog.getAuthor();

        boolean isAuthorized = user.getUserRole().stream()
                .anyMatch(role -> role.getRole().equalsIgnoreCase("ADMIN") || role.getRole().equalsIgnoreCase("MODERATOR")
                || role.getRole().equalsIgnoreCase("USER") || role.getRole().equalsIgnoreCase("AUTHOR"));
        if (!isAuthorized) {
            return ResponseEntity.ok("You aren't authorized to access the blog...!");
        }
//       System.out.println(blog.getAuthor());
        return ResponseEntity.ok(customMapper.toBlogShowDTO(blog));
    }


    public ResponseEntity<?> deleteBlogById(Long id) {
        Blog blog=blogRepo.findById(id).orElseThrow();
        User user=blog.getAuthor();
        boolean isAuthorized = user.getUserRole().stream()
                .anyMatch(role -> role.getRole().equalsIgnoreCase("ADMIN") || role.getRole().equalsIgnoreCase("MODERATOR")
                        || role.getRole().equalsIgnoreCase("Author"));
        if (!isAuthorized) {
            return ResponseEntity.ok("You aren't authorized to delete the blog...!");
        }
        blogRepo.deleteById(id);
        return ResponseEntity.ok("Blog Delete Successfully...!!");
    }


    public ResponseEntity<?> updateBlogById(Long id, BlogDTO blogDTO) {
        Blog blog=blogRepo.findById(id).orElseThrow();
        User user=blog.getAuthor();
        boolean isAuthorized = user.getUserRole().stream()
                .anyMatch(role -> role.getRole().equalsIgnoreCase("ADMIN") || role.getRole().equalsIgnoreCase("MODERATOR")
                        || role.getRole().equalsIgnoreCase("Author"));
        if (!isAuthorized) {
            return ResponseEntity.ok("You aren't authorized to update the blog...!");
        }

        blog.setTitle(blogDTO.getTitle());
        blog.setContent(blogDTO.getContent());
        blogRepo.save(blog);
        return ResponseEntity.ok("Update Successfully...!!");
    }
}
