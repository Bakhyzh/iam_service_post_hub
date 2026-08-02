package com.post_hub.iam_service.service.Impl;

import com.post_hub.iam_service.model.dto.comment.CommentDTO;
import com.post_hub.iam_service.model.request.comment.NewCommentRequest;
import com.post_hub.iam_service.model.response.IamResponse;
import com.post_hub.iam_service.service.CommentService;

public class CommentServiceImpl implements CommentService {
    @Override
    public IamResponse<CommentDTO> createComment(NewCommentRequest request) {
        return null;
    }
}
