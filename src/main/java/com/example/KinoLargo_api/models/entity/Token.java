package com.example.KinoLargo_api.models.entity;

import com.example.KinoLargo_api.enums.TokenType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@jakarta.persistence.Table(name = "tokens")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(unique = true)
    public String token;

    @Enumerated(EnumType.STRING)
    @Column(name = "token_type", nullable = false)
    public TokenType tokenType;

    @Column(nullable = false)
    public boolean revoked;

    @Column(nullable = false)
    public boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;
}