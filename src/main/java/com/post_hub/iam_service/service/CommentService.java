package com.post_hub.iam_service.service;

import com.post_hub.iam_service.model.entity.Comment;
import com.post_hub.iam_service.model.response.IamResponse;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public interface CommentService {
    IamResponse<Comment> createComment(@NotNull NewCommentRequest request);

}
