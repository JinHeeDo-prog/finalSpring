package com.example.fianltest.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return userName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String userName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}