package com.post_hub.iam_service.model.entity;

import com.post_hub.iam_service.model.enums.RegistrationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    public static final String ID_FIELD = "id";
    public static final String USERNAME_NAME_FIELD = "username";
    public static final String EMAIL_NAME_FILED = "email";
    public static final String DELETED_FIELD = "deleted";
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

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Post> posts;
}
