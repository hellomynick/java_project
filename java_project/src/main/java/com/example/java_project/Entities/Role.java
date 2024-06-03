package com.example.java_project.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "role")
public class Role extends AbstractEntity {
    @Getter
    @Setter
    private String roleName;

    @Getter
    @Setter
    private boolean isActive;

    @ManyToMany(mappedBy = "roles")
    private List<Account> accounts;

    @ManyToMany
    @JoinTable(name = "role_access",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "function_id", referencedColumnName = "id"))
    private List<Function> functions;
}
