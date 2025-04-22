package com.example.spring_intro.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String email;
    private String contact;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Blog> blogPost;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserComment> comments;

    @ManyToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private Set<UserRole> userRole;
}

