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
@Table(name = "role_access")
public class RoleAccess extends AbstractEntity {
    @Getter
    @Setter
    @Column(name = "role_id")
    private long roleId;

    @Getter
    @Setter
    @Column(name = "function_id")
    private long functionId;

    @Getter
    @Setter
    private boolean isReadOnly;

    @Getter
    @Setter
    private boolean isReadWrite;

    @Getter
    @Setter
    private boolean isActive;
}
