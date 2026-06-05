package com.post_hub.iam_service.model.constans;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ApiErrorMessage {
    POST_NOT_FOUND_BY_ID("Post with ID: %s was not found"),
    POST_ALREADY_EXISTS("Post with TITLE: %s already exists"),
    ;

    private final String message;
    public String getMessage(Object... args){
        return String.format(message,args);
    }

}
