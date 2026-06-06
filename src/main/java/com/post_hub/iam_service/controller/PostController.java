package com.post_hub.iam_service.controller;

import com.post_hub.iam_service.model.constans.ApiLogMessage;
import com.post_hub.iam_service.model.dto.post.PostDTO;
import com.post_hub.iam_service.model.dto.post.PostSearchDTO;
import com.post_hub.iam_service.model.request.post.NewPostRequest;
import com.post_hub.iam_service.model.request.post.UpdatePostRequest;
import com.post_hub.iam_service.model.response.IamResponse;
import com.post_hub.iam_service.model.response.PaginationResponse;
import com.post_hub.iam_service.service.PostService;
import com.post_hub.iam_service.utils.ApiUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("${end.points.posts}")
public class PostController {
    private final PostService postService;

    @GetMapping("${end.points.id}")
    public ResponseEntity<IamResponse<PostDTO>> getById(@PathVariable(name="id") Integer postId){
        log.trace(ApiLogMessage.NAME_OF_CURRENT_METHOD.getValue(), ApiUtils.getMethodName());
        IamResponse<PostDTO> response = postService.getById(postId);
        return ResponseEntity.ok(response);
    }
    @PostMapping("${end.points.create}")
    public ResponseEntity<IamResponse<PostDTO>> createPost(@RequestBody @Valid NewPostRequest newPostRequest){
        log.trace(ApiLogMessage.NAME_OF_CURRENT_METHOD.getValue(), ApiUtils.getMethodName());
        IamResponse<PostDTO> response = postService.createPost(newPostRequest);
        return ResponseEntity.ok(response);
    }
    @PutMapping("${end.points.id}")
    public ResponseEntity<IamResponse<PostDTO>> updatePost(@PathVariable(name = "id") Integer postId ,@RequestBody @Valid UpdatePostRequest updatePostRequest){
        log.trace(ApiLogMessage.NAME_OF_CURRENT_METHOD.getValue(), ApiUtils.getMethodName());
        IamResponse<PostDTO> response = postService.updatePost(postId,updatePostRequest);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("${end.points.id}")
    public ResponseEntity<Void> softDeleteById(@PathVariable(name = "id") Integer postId){
        log.trace(ApiLogMessage.NAME_OF_CURRENT_METHOD.getValue(), ApiUtils.getMethodName());
        postService.softDeletePost(postId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("${end.points.all}")
    public ResponseEntity<IamResponse<PaginationResponse<PostSearchDTO>>> getAllPosts(
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "limit",defaultValue = "10") int limit
    ){
        log.trace(ApiLogMessage.NAME_OF_CURRENT_METHOD.getValue(),ApiUtils.getMethodName());
        Pageable pageable = PageRequest.of(page,limit);
        IamResponse<PaginationResponse<PostSearchDTO>> response = postService.findAllPosts(pageable);
        return ResponseEntity.ok(response);

    }



}
