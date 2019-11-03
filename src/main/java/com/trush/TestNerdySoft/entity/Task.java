package com.trush.TestNerdySoft.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String text;

    @ManyToMany
    @JoinTable(
            name = "task_users",
            joinColumns = {@JoinColumn(name = "Task_id")},
            inverseJoinColumns = {@JoinColumn(name = "User_id")}
    )
    private List<User> users;


    @ManyToOne
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsers() {
        return users;
    }



    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUsers(User user){
        this.users.add(user);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
