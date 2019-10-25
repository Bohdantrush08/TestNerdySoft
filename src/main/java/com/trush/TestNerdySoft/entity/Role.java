package com.trush.TestNerdySoft.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotEmpty
    private String name;

    @ManyToMany
    @JoinTable(
            name = "role_privilege",
            joinColumns = {@JoinColumn(name = "Role_id")},
            inverseJoinColumns = {@JoinColumn(name = "Privilege_id")}
    )
    private List<Privilege> privileges;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(  List<Privilege> privileges) {
        this.privileges = privileges;
    }
}