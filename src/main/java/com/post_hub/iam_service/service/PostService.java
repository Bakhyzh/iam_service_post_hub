package com.post_hub.iam_service.service;

import com.post_hub.iam_service.model.dto.post.PostDTO;
import com.post_hub.iam_service.model.request.post.NewPostRequest;
import com.post_hub.iam_service.model.request.post.UpdatePostRequest;
import com.post_hub.iam_service.model.response.IamResponse;
import jakarta.validation.constraints.NotNull;

public interface PostService {
    IamResponse<PostDTO> getById(@NotNull Integer postId);
    IamResponse<PostDTO> createPost(@NotNull NewPostRequest newPostRequest);
    IamResponse<PostDTO> updatePost(@NotNull Integer postId ,@NotNull UpdatePostRequest updatePostRequest);
    void softDeletePost(@NotNull Integer postId);
}
