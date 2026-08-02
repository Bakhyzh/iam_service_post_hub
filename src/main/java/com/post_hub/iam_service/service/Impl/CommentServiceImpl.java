package com.post_hub.iam_service.service.Impl;

import com.post_hub.iam_service.mapper.CommentMapper;
import com.post_hub.iam_service.model.dto.comment.CommentDTO;
import com.post_hub.iam_service.model.entity.Comment;
import com.post_hub.iam_service.model.entity.Post;
import com.post_hub.iam_service.model.entity.User;
import com.post_hub.iam_service.model.request.comment.NewCommentRequest;
import com.post_hub.iam_service.model.response.IamResponse;
import com.post_hub.iam_service.repositories.CommentRepository;
import com.post_hub.iam_service.repositories.PostRepository;
import com.post_hub.iam_service.repositories.UserRepository;
import com.post_hub.iam_service.service.CommentService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;
    @Override
    public IamResponse<CommentDTO> createComment(NewCommentRequest request) {
        User user = userRepository.findByTitle(request.getTitle()).getUser();
        Comment comment = commentMapper.createComment(request,user);
        Comment saved = commentRepository.save(comment);
        CommentDTO commentDTO = commentMapper.toCommentDto(saved);
        return IamResponse.creataSuccessful(commentDTO);
    }
}
