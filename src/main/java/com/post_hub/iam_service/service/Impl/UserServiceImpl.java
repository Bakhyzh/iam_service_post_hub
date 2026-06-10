package com.post_hub.iam_service.service.Impl;

import com.post_hub.iam_service.mapper.UserMapper;
import com.post_hub.iam_service.model.constans.ApiErrorMessage;
import com.post_hub.iam_service.model.dto.user.UserDTO;
import com.post_hub.iam_service.model.entity.User;
import com.post_hub.iam_service.model.exception.NotFoundException;
import com.post_hub.iam_service.model.response.IamResponse;
import com.post_hub.iam_service.repositories.UserRepository;
import com.post_hub.iam_service.service.UserService;
import lombok.RequiredArgsConstructor;
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
}
