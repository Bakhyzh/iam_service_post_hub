package com.post_hub.iam_service.service;

import com.post_hub.iam_service.model.dto.post.CommentDTO;
import com.post_hub.iam_service.model.entity.Comment;
import com.post_hub.iam_service.model.request.comment.NewCommentRequest;
import com.post_hub.iam_service.model.response.IamResponse;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public interface CommentService {
    IamResponse<CommentDTO> createComment(@NotNull NewCommentRequest request);

}
