package com.post_hub.iam_service.service;

import com.post_hub.iam_service.model.dto.post.PostDTO;
import com.post_hub.iam_service.model.dto.post.PostSearchDTO;
import com.post_hub.iam_service.model.request.post.NewPostRequest;
import com.post_hub.iam_service.model.request.post.PostSearchRequest;
import com.post_hub.iam_service.model.request.post.UpdatePostRequest;
import com.post_hub.iam_service.model.response.IamResponse;
import com.post_hub.iam_service.model.response.PaginationResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Pageable;


public interface PostService {
    IamResponse<PostDTO> getById(@NotNull Integer postId);
    IamResponse<PostDTO> createPost(@NotNull Integer userId,@NotNull NewPostRequest newPostRequest);
    IamResponse<PostDTO> updatePost(@NotNull Integer postId ,@NotNull UpdatePostRequest updatePostRequest);
    void softDeletePost(@NotNull Integer postId);
    IamResponse<PaginationResponse<PostSearchDTO>> findAllPosts(Pageable pageable);
    IamResponse<PaginationResponse<PostSearchDTO>> searchPost(@NotNull PostSearchRequest request,Pageable pageable);
}
