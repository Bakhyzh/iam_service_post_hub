package com.post_hub.iam_service.service.Impl;

import com.post_hub.iam_service.mapper.PostMapper;
import com.post_hub.iam_service.model.constans.ApiErrorMessage;
import com.post_hub.iam_service.model.dto.post.PostDTO;
import com.post_hub.iam_service.model.entity.Post;
import com.post_hub.iam_service.model.exception.DataExistException;
import com.post_hub.iam_service.model.exception.NotFoundException;
import com.post_hub.iam_service.model.request.post.NewPostRequest;
import com.post_hub.iam_service.model.request.post.UpdatePostRequest;
import com.post_hub.iam_service.model.response.IamResponse;
import com.post_hub.iam_service.repositories.PostRepository;
import com.post_hub.iam_service.service.PostService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    @Override
    public IamResponse<PostDTO> getById(@NotNull Integer postId) {
        Post post =postRepository.findByIdAndDeletedFalse(postId)
                .orElseThrow(()-> new NotFoundException(ApiErrorMessage.POST_NOT_FOUND_BY_ID.getMessage(postId)));
        PostDTO postDTO = postMapper.toPostDto(post);
        return IamResponse.creataSuccessful(postDTO);
    }

    @Override
    public IamResponse<PostDTO> createPost(@NotNull NewPostRequest newPostRequest) {
        if(postRepository.existsByTitle(newPostRequest.getTitle())){
            throw new DataExistException(ApiErrorMessage.POST_ALREADY_EXISTS.getMessage(newPostRequest.getTitle()));
        }
        Post post = postMapper.createPost(newPostRequest);
        Post savedPost = postRepository.save(post);
        PostDTO postDTO = postMapper.toPostDto(savedPost);
        return IamResponse.creataSuccessful(postDTO);
    }

    @Override
    public IamResponse<PostDTO> updatePost(@NotNull Integer postId,@NotNull UpdatePostRequest updatePostRequest) {
        Post post =postRepository.findByIdAndDeletedFalse(postId)
                .orElseThrow(()-> new NotFoundException(ApiErrorMessage.POST_NOT_FOUND_BY_ID.getMessage(postId)));
        postMapper.updatePost(post,updatePostRequest);
        post.setUpdated(LocalDateTime.now());
        post = postRepository.save(post);
        PostDTO postDTO = postMapper.toPostDto(post);
        return IamResponse.creataSuccessful(postDTO);
    }

    @Override
    public void softDeletePost(Integer postId) {
        Post post =postRepository.findByIdAndDeletedFalse(postId)
                .orElseThrow(()-> new NotFoundException(ApiErrorMessage.POST_NOT_FOUND_BY_ID.getMessage(postId)));
        post.setDeleted(true);
        postRepository.save(post);
    }

}
