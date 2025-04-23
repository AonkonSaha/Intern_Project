package com.example.spring_intro.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="user_blog")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    @Column(length = 5000)
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name="author_id")
    private User author;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    private List<UserComment> userComments;

    @PrePersist
    public void prePersist() {
        createdAt = updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

   private Double rating;

}
