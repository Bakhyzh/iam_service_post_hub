package com.post_hub.iam_service.advice;

import com.post_hub.iam_service.model.constans.ApiConstans;
import com.post_hub.iam_service.model.exception.DataExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Slf4j
@ControllerAdvice
public class CommonControllerAdvise {
    @ExceptionHandler
    @ResponseBody
    protected ResponseEntity<String> handleNotFoundException(Exception ex){
        logStackTrace(ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
    @ExceptionHandler(DataExistException.class)
    @ResponseBody
    protected ResponseEntity<String> handleDataExistsException(DataExistException ex){
        logStackTrace(ex);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        logStackTrace(ex);
        Map<String,String> errors = new HashMap<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()){
            String errorMessage = error.getDefaultMessage();
            errors.put("error",errorMessage);
        }
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
    private void logStackTrace(Exception ex){
        StringBuilder stacktrace = new StringBuilder();

        stacktrace.append(ApiConstans.ANSI_RED);

        stacktrace.append(ex.getMessage()).append(ApiConstans.BREAK_LINE);

        if (Objects.nonNull(ex.getMessage())){
            stacktrace.append(ex.getCause()).append(ApiConstans.BREAK_LINE);
        }
        Arrays.stream(ex.getStackTrace())
                .filter(st -> st.getClassName().startsWith(ApiConstans.TIME_ZONE_PACKAGE_NAME))
                .forEach(st -> stacktrace
                        .append(st.getClassName())
                        .append(".")
                        .append(st.getMethodName())
                        .append("( ")
                        .append(st.getLineNumber())
                        .append(" )")
                );
        log.error(stacktrace.append(ApiConstans.ANSI_WHITE).toString());
    }
}
