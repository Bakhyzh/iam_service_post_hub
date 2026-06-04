package com.post_hub.iam_service.model.constans;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ApiLogMessage {
    POST_INFO_BY_ID("Receiving post with id: {}"),
    NAME_OF_CURRENT_METHOD("Current method: {}")
    ;
    private final String value;



}
