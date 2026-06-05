package com.post_hub.iam_service.mapper;

import com.post_hub.iam_service.model.dto.post.PostDTO;
import com.post_hub.iam_service.model.entity.Post;
import com.post_hub.iam_service.model.request.post.NewPostRequest;
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
public interface PostMapper {
    @Mapping(source = "id",target = "id")
    @Mapping(source ="title",target="title")
    @Mapping(source ="content",target="content")
    @Mapping(source ="likes",target="likes")
    @Mapping(source ="created",target="created",dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    PostDTO toPostDto(Post post);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "created",ignore = true)
    Post createPost(NewPostRequest newPostRequest);
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "created",ignore = true)
    void updatePost(@MappingTarget Post post , UpdatePostRequest updatePostRequest);
}
