package com.post_hub.iam_service.service.impl;

import com.post_hub.iam_service.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private List<String> comments = new ArrayList<>();
    @Override
    public void createComment(String commentContent){
        comments.add(commentContent);
    }
}
