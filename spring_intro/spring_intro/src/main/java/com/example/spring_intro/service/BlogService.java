package com.example.spring_intro.service;

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

    public BlogDTO addBlog(BlogDTO blogDTO) {

            Optional<User> user=userRepo.findById(blogDTO.getAuthorUserId());
            if(user.isEmpty())
            {
                return null;
            }
            Blog blog = blogMapper.toBlog(blogDTO);
            blog.setAuthor(user.get());
            UserComment userComment=userCommentRepo.findByAuthor(user.get().getUserName());
            blog.setUserComments(userComment != null ? List.of(userComment) : new ArrayList<>());
            blogRepo.save(blog);
            return blogMapper.toBlogDTO(blog);
    }
    public BlogDTO fetchBlogById(Long id) {

        Optional<Blog> blog=blogRepo.findById(id);
        if(blog.isEmpty())
        {
            return null;
        }
        return blogMapper.toBlogDTO(blog.get());
    }
    public void deleteBlogById(Long blogId,Long userId) {
        Optional<Blog> blog=blogRepo.findById(blogId);
        Optional<User> user=userRepo.findById(userId);
        if(user.isEmpty())
        {
            return;
        }
        blogRepo.deleteById(blogId);
    }
    public BlogDTO updateBlogById(Long blogId,Long userId, BlogDTO blogDTO) {
        Optional<Blog> blog=blogRepo.findById(blogId);
        if(blog.isEmpty())
        {
            return null;
        }
        blog.get().setTitle(blogDTO.getTitle());
        blog.get().setContent(blogDTO.getContent());
        blogRepo.save(blog.get());
        return blogMapper.toBlogDTO(blog.get());
    }
}
