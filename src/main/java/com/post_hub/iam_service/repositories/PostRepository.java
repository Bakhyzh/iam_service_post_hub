package com.post_hub.iam_service.repositories;

import com.post_hub.iam_service.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
    boolean existsByTitle(String title);
    Optional<Post> findByIdAndDeletedFalse(Integer postId);
}
