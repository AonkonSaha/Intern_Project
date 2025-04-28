package com.example.spring_intro.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include

    private Long id;
    @Column(name = "user_name")
    private String userName;
    private String email;
    private String contact;
    private String password;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Blog> blogPost;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserComment> comments;

    @ManyToMany(mappedBy = "users",fetch = FetchType.EAGER)
    private Set<UserRole> userRole;
}

