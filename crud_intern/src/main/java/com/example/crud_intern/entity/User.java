package com.example.crud_intern.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String userName;
    String email;
    String contact;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "blog")
    Blog blogPost;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_comment")
    UserComment userComment;
}
