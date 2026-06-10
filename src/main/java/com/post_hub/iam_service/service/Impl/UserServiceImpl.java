package com.post_hub.iam_service.service.Impl;

import com.post_hub.iam_service.mapper.UserMapper;
import com.post_hub.iam_service.model.constans.ApiErrorMessage;
import com.post_hub.iam_service.model.dto.user.UserDTO;
import com.post_hub.iam_service.model.dto.user.UserSearchDTO;
import com.post_hub.iam_service.model.entity.User;
import com.post_hub.iam_service.model.exception.DataExistException;
import com.post_hub.iam_service.model.exception.NotFoundException;
import com.post_hub.iam_service.model.request.post.UpdatePostRequest;
import com.post_hub.iam_service.model.request.user.NewUserRequest;
import com.post_hub.iam_service.model.request.user.UpdateUserRequest;
import com.post_hub.iam_service.model.request.user.UserSearchRequest;
import com.post_hub.iam_service.model.response.IamResponse;
import com.post_hub.iam_service.model.response.PaginationResponse;
import com.post_hub.iam_service.repositories.UserRepository;
import com.post_hub.iam_service.repositories.criteria.UserSearchCriteria;
import com.post_hub.iam_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public IamResponse<UserDTO> getById(Integer userId) {
        User user = userRepository.findByIdAndDeletedFalse(userId).orElseThrow(()-> new NotFoundException(ApiErrorMessage.USER_NOT_FOUND_BY_ID.getMessage(userId)));
        UserDTO userDTO = userMapper.toDto(user);
        return IamResponse.creataSuccessful(userDTO);
    }

    @Override
    public IamResponse<UserDTO> createUser(NewUserRequest newUserRequest) {
        if(userRepository.existsByUsername(newUserRequest.getUsername())){
            throw new DataExistException(ApiErrorMessage.USER_ALREADY_EXISTS.getMessage(newUserRequest.getUsername()));
        }
        if(userRepository.existsByEmail(newUserRequest.getEmail())){
            throw new DataExistException(ApiErrorMessage.USER_ALREADY_EXISTS.getMessage(newUserRequest.getEmail()));
        }
        User user = userMapper.createUser(newUserRequest);
        User saveduser = userRepository.save(user);
        UserDTO userDTO = userMapper.toDto(saveduser);

        return IamResponse.creataSuccessful(userDTO);
    }

    @Override
    public IamResponse<UserDTO> updateUser(Integer userId,UpdateUserRequest request) {
        User user = userRepository.findByIdAndDeletedFalse(userId).orElseThrow(
                ()->new NotFoundException(ApiErrorMessage.USER_NOT_FOUND_BY_ID.getMessage(userId)));
        userMapper.updateUser(user,request);
        user.setUpdated(LocalDateTime.now());
        user = userRepository.save(user);
        UserDTO userDTO = userMapper.toDto(user);
        return IamResponse.creataSuccessful(userDTO);
    }

    @Override
    public void softDelete(Integer id) {
        User user = userRepository.findByIdAndDeletedFalse(id).
                orElseThrow( () -> new NotFoundException(ApiErrorMessage.USER_NOT_FOUND_BY_ID.getMessage(id)));
        user.setDeleted(true);
        userRepository.save(user);
    }

    @Override
    public IamResponse<PaginationResponse<UserSearchDTO>> findAllUsers(Pageable pageable) {
        Page<UserSearchDTO> users = userRepository.findAll(pageable)
                .map(userMapper::toUserSearchDTO);
        PaginationResponse<UserSearchDTO> response = new PaginationResponse<>(
                users.getContent(),
                new PaginationResponse.Pagination(
                        users.getTotalElements(),
                        pageable.getPageSize(),
                        users.getNumber()+1,
                        users.getTotalPages()
                )
        );
        return IamResponse.creataSuccessful(response);
    }
    @Override
    public IamResponse<PaginationResponse<UserSearchDTO>> searchUsers(UserSearchRequest request,Pageable pageable){
        Specification<User> specification = new UserSearchCriteria(request);
        Page<UserSearchDTO> users = userRepository.findAll(specification,pageable)
                .map(userMapper::toUserSearchDTO);
        PaginationResponse<UserSearchDTO> response =  PaginationResponse.<UserSearchDTO>builder()
                .content(users.getContent())
                .pagination(PaginationResponse.Pagination.builder()
                        .total(users.getTotalElements())
                        .limit(pageable.getPageSize())
                        .page(users.getNumber())
                        .pages(users.getTotalPages())
                        .build())
                .build();


        return IamResponse.creataSuccessful(response);
    }

}
