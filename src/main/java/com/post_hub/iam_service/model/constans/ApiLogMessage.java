package com.post_hub.iam_service.model.constans;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ApiLogMessage {
    POST_INFO_BY_ID("Receiving post with id: %s"),
    ;
    private final String message;

    public String getMessage(Object... args){
        return String.format(message,args);
    }

}
