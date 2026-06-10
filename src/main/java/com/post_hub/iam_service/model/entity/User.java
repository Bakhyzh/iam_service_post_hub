package com.post_hub.iam_service.model.entity;

import com.post_hub.iam_service.model.enums.RegistrationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max=30)
    @Column(nullable = false,length = 30)
    private String username;

    @Size(max=80)
    @Column(nullable = false,length = 80)
    private String password;

    @Size(max=50)
    @Column(nullable = false,length = 50)
    private String email;

    @Column(nullable = false)
    private LocalDateTime created = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updated =LocalDateTime.now();

    @Column()
    private LocalDateTime last_login;

    @Column(nullable = false)
    private boolean deleted;

    @Enumerated(EnumType.STRING)
    @Column(name = "registration_status",nullable = false)
    private RegistrationStatus registrationStatus;

}
