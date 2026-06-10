package com.post_hub.iam_service.model.dto.user;

import com.post_hub.iam_service.model.enums.RegistrationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchDTO implements Serializable {
    private Integer id;
    private String username;
    private String email;
    private LocalDateTime created;
    private LocalDateTime lastLogin;
    private boolean deleted;

    private RegistrationStatus registrationStatus;

}
