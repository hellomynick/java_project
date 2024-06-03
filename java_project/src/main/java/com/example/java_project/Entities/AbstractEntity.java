package com.example.java_project.Entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
public class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Getter
    @Setter
    private LocalDateTime dateCreate;

    @Getter
    @Setter
    private LocalDateTime dateUpdate;

    @Getter
    @Setter
    private LocalDateTime createBy;

    @Getter
    @Setter
    private LocalDateTime updateBy;
}
