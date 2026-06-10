package com.post_hub.iam_service.controller;

import com.post_hub.iam_service.model.constans.ApiLogMessage;
import com.post_hub.iam_service.model.dto.user.UserDTO;
import com.post_hub.iam_service.model.dto.user.UserSearchDTO;
import com.post_hub.iam_service.model.request.user.NewUserRequest;
import com.post_hub.iam_service.model.request.user.UpdateUserRequest;
import com.post_hub.iam_service.model.request.user.UserSearchRequest;
import com.post_hub.iam_service.model.response.IamResponse;
import com.post_hub.iam_service.model.response.PaginationResponse;
import com.post_hub.iam_service.service.UserService;
import com.post_hub.iam_service.utils.ApiUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("${end.points.users}")
public class UserController {
    private final UserService userService;

    @GetMapping("${end.points.id}")
    public ResponseEntity<IamResponse<UserDTO>> getById(@PathVariable(name = "id") Integer id){
        log.trace(ApiLogMessage.NAME_OF_CURRENT_METHOD.getValue(), ApiUtils.getMethodName());
        IamResponse<UserDTO> response = userService.getById(id);
        return ResponseEntity.ok(response);
    }
    @PostMapping("${end.points.create}")
    public ResponseEntity<IamResponse<UserDTO>> createUser(@RequestBody @Valid NewUserRequest request){
        log.trace(ApiLogMessage.NAME_OF_CURRENT_METHOD.getValue(),ApiUtils.getMethodName());
        IamResponse<UserDTO> response= userService.createUser(request);
        return ResponseEntity.ok(response);

    }
    @PutMapping("${end.points.id}")
    public ResponseEntity<IamResponse<UserDTO>> updateUser(
            @PathVariable(name = "id") Integer id,
            @RequestBody @Valid UpdateUserRequest request){
        log.trace(ApiLogMessage.NAME_OF_CURRENT_METHOD.getValue(),ApiUtils.getMethodName());
        IamResponse response = userService.updateUser(id,request);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("${end.points.id}")
    public ResponseEntity<IamResponse<UserDTO>> softDeleteUser(
            @PathVariable(name="id") Integer id){
        log.trace(ApiLogMessage.NAME_OF_CURRENT_METHOD.getValue(),ApiUtils.getMethodName());
        userService.softDelete(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("${end.points.all}")
    public ResponseEntity<IamResponse<PaginationResponse<UserSearchDTO>>> getAllUsers(
            @RequestParam(name="page",defaultValue = "0") int page,
            @RequestParam(name = "limit",defaultValue = "10")int limit){
        log.trace(ApiLogMessage.NAME_OF_CURRENT_METHOD.getValue(),ApiUtils.getMethodName());

        Pageable pageable = PageRequest.of(page,limit);
        IamResponse<PaginationResponse<UserSearchDTO>> response = userService.findAllUsers(pageable);
        return ResponseEntity.ok(response);




    }
    @PostMapping("${end.points.search}")
    public ResponseEntity<IamResponse<PaginationResponse<UserSearchDTO>>> searchUsers(
            @RequestBody @Valid UserSearchRequest request,
            @RequestParam(name="page",defaultValue = "0") int page,
            @RequestParam(name = "limit",defaultValue = "10")int limit){
        log.trace(ApiLogMessage.NAME_OF_CURRENT_METHOD.getValue(),ApiUtils.getMethodName());
        Pageable pageable = PageRequest.of(page,limit);
        IamResponse<PaginationResponse<UserSearchDTO>> response = userService.searchUsers(request,pageable);
        return ResponseEntity.ok(response);
    }


}
