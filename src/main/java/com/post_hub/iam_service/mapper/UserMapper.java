package com.post_hub.iam_service.mapper;

import com.post_hub.iam_service.model.dto.user.UserDTO;
import com.post_hub.iam_service.model.entity.User;
import com.post_hub.iam_service.model.enums.RegistrationStatus;
import com.post_hub.iam_service.model.request.user.NewUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.io.Serializable;

@Mapper(
        componentModel = "spring",
        imports = RegistrationStatus.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserMapper  {
    @Mapping(source = "last_login",target = "lastLogin")
    UserDTO toDto(User user);
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "created",ignore = true)
    @Mapping(target = "registrationStatus",expression = "java(RegistrationStatus.ACTIVE)")
    User createUser(NewUserRequest newUserRequest);
}
