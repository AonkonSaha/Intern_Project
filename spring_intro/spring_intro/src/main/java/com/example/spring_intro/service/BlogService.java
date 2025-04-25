package com.example.spring_intro.service;

import com.example.spring_intro.exception.BlogNotFoundException;
import com.example.spring_intro.exception.UserNotFoundException;
import com.example.spring_intro.model.dto.BlogDTO;
import com.example.spring_intro.model.entity.Blog;
import com.example.spring_intro.model.entity.User;
import com.example.spring_intro.model.entity.UserComment;
import com.example.spring_intro.model.mapper.BlogMapper;
import com.example.spring_intro.repository.BlogRepo;
import com.example.spring_intro.repository.UserCommentRepo;
import com.example.spring_intro.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlogService{

    private final BlogMapper blogMapper;
    private final UserRepo userRepo;
    private final BlogRepo blogRepo;
    private final UserCommentRepo userCommentRepo;
    private final RoleService roleService;

    public BlogDTO addBlog(BlogDTO blogDTO) throws UserNotFoundException {

            Optional<User> user=userRepo.findById(blogDTO.getAuthorUserId());
            if(user.isEmpty())
            {
                throw new UserNotFoundException("User doesn't exit..!!..!...!");
            }
            Blog blog = blogMapper.toBlog(user.get(),blogDTO);
            blogRepo.save(blog);
            return blogMapper.toBlogDTO(blog);
    }
    public BlogDTO fetchBlogById(Long id) throws BlogNotFoundException {

        Optional<Blog> blog=blogRepo.findById(id);
        if(blog.isEmpty())
        {
           throw new BlogNotFoundException("Blog doesn't exit..!!..!");
        }
        return blogMapper.toBlogDTO(blog.get());
    }
    public void deleteBlogById(Long blogId) throws BlogNotFoundException {
        Optional<Blog> blog=blogRepo.findById(blogId);
        if(blog.isEmpty())
        {
            throw new BlogNotFoundException("Blog doesn't exit..!!..!");
        }
        blogRepo.deleteById(blogId);
    }
    public BlogDTO updateBlogById(Long blogId,BlogDTO blogDTO) throws BlogNotFoundException {
        Optional<Blog> blog=blogRepo.findById(blogId);
        if(blog.isEmpty())
        {
            throw new BlogNotFoundException("Blog doesn't exit..!!..!");
        }
        blog.get().setTitle(blogDTO.getTitle());
        blog.get().setContent(blogDTO.getContent());
        blogRepo.save(blog.get());
        return blogMapper.toBlogDTO(blog.get());
    }
}
