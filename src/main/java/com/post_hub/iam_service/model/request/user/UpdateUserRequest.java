package com.post_hub.iam_service.model.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest implements Serializable {
    @NotBlank(message = "Username cannot be Empty")
    @Size(max=50)
    private String username;
    @NotBlank(message = "password cannot be Empty")
    @Size(max=50)
    private String password;
    @NotBlank(message = "email cannot be Empty")
    @Size(max=50)
    private String email;
}
