package com.example.java_project.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "account")
public class Account extends AbstractEntity {
    @Getter
    @Setter
    private String key;

    @Getter
    @Setter
    @Column(unique = true)
    private String userName;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private boolean isActive;

    @Getter
    @Setter
    @Column(unique = true)
    private String email;

    @Getter
    @Setter
    private String phoneNumber;

    @Getter
    @Setter
    private String avatarName;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
}
