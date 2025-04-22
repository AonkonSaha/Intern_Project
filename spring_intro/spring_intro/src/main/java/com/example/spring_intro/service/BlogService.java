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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService{
    @Autowired
    CustomMapper customMapper;
    @Autowired
    UserRepo userRepo;
    @Autowired
    BlogRepo blogRepo;
    @Autowired
    private UserCommentRepo userCommentRepo;



    public String addBlog(String userName, BlogDTO blogDTO) {
        try
        {
            Blog blog = customMapper.toBlog(blogDTO);
            User user=userRepo.findByUserName(userName);
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


    public BlogShowDTO fetchBlogById(Long id) {
        Blog blog=blogRepo.findById(id).orElseThrow();
//       System.out.println(blog.getAuthor());
        return customMapper.toBlogShowDTO(blog);
    }


    public String deleteBlogById(Long id) {
        blogRepo.deleteById(id);
        return "Blog Delete Successfully...!!";
    }


    public String updateBlogById(Long id, BlogDTO blogDTO) {
        Blog blog=blogRepo.findById(id).orElseThrow();

        blog.setTitle(blogDTO.getTitle());
        blog.setContent(blogDTO.getContent());
        blogRepo.save(blog);
        return "Update Successfully...!!";
    }
}
