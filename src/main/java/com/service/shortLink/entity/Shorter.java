package com.service.shortLink.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Shorter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "hash")
    private String hash;
    @Column(name = "original_url")
    private String originalUrl;
    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private ZonedDateTime createdAt;

    public Shorter(String hash, String originalUrl, ZonedDateTime createdAt) {
        this.hash = hash;
        this.originalUrl = originalUrl;
        this.createdAt = createdAt;
    }
}
