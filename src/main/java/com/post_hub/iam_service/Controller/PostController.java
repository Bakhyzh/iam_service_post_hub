package com.post_hub.iam_service.Controller;

import com.post_hub.iam_service.model.entity.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("posts")
public class PostController {
    @GetMapping
    public ResponseEntity<Post> getPosts(){
        Post post = new Post();
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
}
