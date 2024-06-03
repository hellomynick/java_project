package com.example.java_project.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Setter
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_role")
public class UserRole extends AbstractEntity {
    @Getter
    @Setter
    @Column(name = "user_id")
    private long userId;

    @Getter
    @Setter
    @Column(name = "role_id")
    private long roleId;
}
