package com.post_hub.iam_service.controller;

import com.post_hub.iam_service.service.impl.PostServicelmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostServicelmpl postServicelmpl;
    @Autowired
    public PostController(PostServicelmpl postServicelmpl) {
        this.postServicelmpl = postServicelmpl;
    }
    @PostMapping("/create")
    public ResponseEntity<String> createPost(@RequestBody Map<String,Object> requestBody){
        String title = (String) requestBody.get("title");
        String content = (String) requestBody.get("content");
        String postContent = "Title : "+title + ", '\n'Content : "+ content+"'\n'";
        postServicelmpl.createPost(postContent);
        return new ResponseEntity<>("Post created with Title : "+title, HttpStatus.OK);
    }


}
