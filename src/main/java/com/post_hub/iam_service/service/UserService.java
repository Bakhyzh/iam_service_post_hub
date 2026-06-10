package com.post_hub.iam_service.service;

import com.post_hub.iam_service.model.dto.user.UserDTO;
import com.post_hub.iam_service.model.dto.user.UserSearchDTO;
import com.post_hub.iam_service.model.request.post.UpdatePostRequest;
import com.post_hub.iam_service.model.request.user.NewUserRequest;
import com.post_hub.iam_service.model.request.user.UpdateUserRequest;
import com.post_hub.iam_service.model.request.user.UserSearchRequest;
import com.post_hub.iam_service.model.response.IamResponse;
import com.post_hub.iam_service.model.response.PaginationResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Pageable;

public interface UserService {
    IamResponse<UserDTO> getById(@NotNull Integer userId);
    IamResponse<UserDTO> createUser(@NotNull NewUserRequest newUserRequest);

    IamResponse updateUser(Integer userId,UpdateUserRequest request);

    void softDelete(@NotNull Integer id);

    IamResponse<PaginationResponse<UserSearchDTO>> findAllUsers(Pageable pageable);
    IamResponse<PaginationResponse<UserSearchDTO>> searchUsers(UserSearchRequest request, Pageable pageable);
}
