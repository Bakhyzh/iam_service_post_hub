package com.post_hub.iam_service.service.Impl;

import com.post_hub.iam_service.mapper.UserMapper;
import com.post_hub.iam_service.model.constans.ApiErrorMessage;
import com.post_hub.iam_service.model.dto.user.UserDTO;
import com.post_hub.iam_service.model.entity.User;
import com.post_hub.iam_service.model.exception.DataExistException;
import com.post_hub.iam_service.model.exception.NotFoundException;
import com.post_hub.iam_service.model.request.user.NewUserRequest;
import com.post_hub.iam_service.model.response.IamResponse;
import com.post_hub.iam_service.repositories.UserRepository;
import com.post_hub.iam_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public IamResponse<UserDTO> getById(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new NotFoundException(ApiErrorMessage.USER_NOT_FOUND_BY_ID.getMessage(userId)));
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
}
