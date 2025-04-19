package com.example.Blog_App.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private long userId;

    @Column(name="username")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "ph-no")
    private String phNo;

    @OneToMany(mappedBy = "author",fetch = FetchType.EAGER)
    private List<Blog> blogs;

}
