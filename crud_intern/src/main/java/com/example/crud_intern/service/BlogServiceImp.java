package com.example.crud_intern.service;

import com.example.crud_intern.dto.BlogDTO;
import com.example.crud_intern.dto.BlogShowDTO;
import com.example.crud_intern.entity.Blog;
import com.example.crud_intern.entity.User;
import com.example.crud_intern.entity.UserComment;
import com.example.crud_intern.mapping.CustomMapper;
import com.example.crud_intern.repo.BlogRepo;
import com.example.crud_intern.repo.UserCommentRepo;
import com.example.crud_intern.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BlogServiceImp implements BlogService{
    @Autowired
    CustomMapper customMapper;
    @Autowired
    UserRepo userRepo;
    @Autowired
    BlogRepo blogRepo;
    @Autowired
    private UserCommentRepo userCommentRepo;


    @Override
    public String addBlog(String userName,BlogDTO blogDTO) {
        try
        {
            Blog blog = customMapper.toBlog(blogDTO);
            User user=userRepo.findByUserName(userName);
            UserComment userComment=userCommentRepo.findByAuthor(userName);
            blog.setAuthors(user != null ? List.of(user) : new ArrayList<>());
            blog.setUserComments(userComment != null ? List.of(userComment) : new ArrayList<>());
            blogRepo.save(blog);
            return "Blog Create Successfully...!!";
        }catch (Exception e)
        {
            e.printStackTrace();
            return "error: "+e.getMessage();
        }


    }

    @Override
    public BlogShowDTO fetchBlogById(Long id) {
        Blog blog=blogRepo.findById(id).get();
        return customMapper.toBlogShowDTO(blog);
    }

    @Override
    public String deleteBlogById(Long id) {
        blogRepo.deleteById(id);
        return "Blog Delete Successfully...!!";
    }

    @Override
    public String updateBlogById(Long id, BlogDTO blogDTO) {
        Blog blog=customMapper.toBlog(blogDTO);
        User user=userRepo.findById(id).orElseThrow();
        UserComment userComment=userCommentRepo.findByAuthor(user.getUserName());
        blog.setAuthors(user != null ? List.of(user) : new ArrayList<>());
        blog.setUserComments(userComment != null ? List.of(userComment) : new ArrayList<>());
        blogRepo.save(blog);
        return "";
    }
}
