package com.post_hub.iam_service.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServicelmpl implements PostService{
    private final List<String> posts = new ArrayList<>();
    @Override
    public void createPost(String postContent){
        posts.add(postContent);
    }


}
