package com.post_hub.iam_service.mapper;

import com.post_hub.iam_service.model.dto.comment.CommentDTO;
import com.post_hub.iam_service.model.dto.post.PostDTO;
import com.post_hub.iam_service.model.dto.post.PostSearchDTO;
import com.post_hub.iam_service.model.entity.Post;
import com.post_hub.iam_service.model.entity.User;
import com.post_hub.iam_service.model.request.comment.NewCommentRequest;
import com.post_hub.iam_service.model.request.post.UpdatePostRequest;
import org.hibernate.type.descriptor.DateTimeUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Objects;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy  = NullValuePropertyMappingStrategy.IGNORE,
        imports = {DateTimeUtils.class, Objects.class}
)
public interface CommentMapper {

    CommentDTO toCommentDto(Post post);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "created",ignore = true)
    @Mapping(source = "user",target = "user")
    @Mapping(target = "createdBy",source= "user.username")
    Post createComment(NewCommentRequest newRequest, User user);
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "created",ignore = true)
    void updatePost(@MappingTarget Post post , UpdatePostRequest updatePostRequest);


    @Mapping (target = "createdBy", source = "user.username" )
    PostSearchDTO toPostSearchDto(Post post);
}
