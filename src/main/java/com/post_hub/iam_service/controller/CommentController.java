package com.post_hub.iam_service.controller;

import com.post_hub.iam_service.service.CommentService;
import com.post_hub.iam_service.service.impl.CommentServiceImpl;
import com.post_hub.iam_service.service.impl.SecondCommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    private final CommentService defaultcommentService;
    private final CommentService SecondcommentService;
    @Autowired

    public CommentController(CommentService defaultcommentService,@Qualifier("advancedCommentService") CommentService secondcommentService) {
        this.defaultcommentService = defaultcommentService;
        SecondcommentService = secondcommentService;
    }

    @PostMapping("/createDefaultComment")
    public ResponseEntity<String> createDefaultComment(@RequestBody Map<String,Object> requestBody){
        String content = (String) requestBody.get("content");
        defaultcommentService.createComment(content);
        return new ResponseEntity<>("createDefaultComment Comment add with Content : "+content, HttpStatus.OK);
    }
    @PostMapping("/createSecondComment")
    public ResponseEntity<String> createSecondComment(@RequestBody Map<String,Object> requestBody){
        String content = (String) requestBody.get("content");
        SecondcommentService.createComment(content);
        return new ResponseEntity<>("createSecondComment to Second add with Content : "+content, HttpStatus.OK);
    }
}
