package com.example.java_project.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "function")
public class Function extends AbstractEntity {
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private boolean isActive;

    @ManyToMany(mappedBy = "functions")
    private List<Role> roles;
}
