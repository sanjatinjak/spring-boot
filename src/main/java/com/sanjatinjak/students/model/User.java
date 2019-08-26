package com.sanjatinjak.students.model;

import javax.persistence.*;

@Entity
@Table(name="users", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username", nullable = false)
    private String username;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="role_id", nullable = false)
    private int role_id;

    public User(){

    }

    public User(String username, String password) {
        this.username=username;
        this.password=password;
    }

    public User(Long userId, String userName, String encrytedPassword) {
        this.id=userId;
        this.username=userName;
        this.password=encrytedPassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }
}
