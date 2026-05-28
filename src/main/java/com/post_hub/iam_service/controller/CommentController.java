package com.post_hub.iam_service.controller;

import com.post_hub.iam_service.service.CommentService;
import com.post_hub.iam_service.service.impl.CommentServiceImpl;
import com.post_hub.iam_service.service.impl.SecondCommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private CommentService commentService;
    @Autowired
    public void setCommentService(CommentServiceImpl commentServiceimpl){
        this.commentService=commentServiceimpl;
    }
    @PostMapping("/create")
    public ResponseEntity<String> addComment(@RequestBody Map<String,Object> requestBody){
        String content = (String) requestBody.get("content");
        commentService.createComment(content);
        return new ResponseEntity<>("Comment add with Content : "+content, HttpStatus.OK);
    }
    @PostMapping("/switch")
    public ResponseEntity<String> switchToSecondService(@RequestBody Map<String,Object> requestBody){
        this.commentService= new SecondCommentServiceImpl();
        String content = (String) requestBody.get("content");
        commentService.createComment(content);
        return new ResponseEntity<>("Switched to Second add with Content : "+content, HttpStatus.OK);
    }
}
