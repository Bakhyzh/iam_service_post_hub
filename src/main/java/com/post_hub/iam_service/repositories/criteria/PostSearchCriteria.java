package com.post_hub.iam_service.repositories.criteria;

import com.post_hub.iam_service.model.entity.Post;
import com.post_hub.iam_service.model.request.post.PostSearchRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class PostSearchCriteria implements Specification<Post> {
    private PostSearchRequest postSearchRequest;

    @Override
    public Predicate toPredicate(@NonNull Root<Post> root,
                                 CriteriaQuery<?> query,
                                 @NonNull CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(postSearchRequest.getTitle())){
            predicates.add(criteriaBuilder.like(root.get(Post.TITLE_NAME_FIELD),"%" + postSearchRequest.getTitle() + "%"));
        }
        if (Objects.nonNull(postSearchRequest.getContent())){
            predicates.add(criteriaBuilder.like(root.get(Post.CONTENT_NAME_FIELD),"%" + postSearchRequest.getContent() + "%"));
        }
        if (Objects.nonNull(postSearchRequest.getLikes())){
            predicates.add(criteriaBuilder.equal(root.get(Post.LIKES_NAME_FIELD),postSearchRequest.getLikes()));
        }
        if (Objects.nonNull(postSearchRequest.getDeleted())){
            predicates.add(criteriaBuilder.equal(root.get(Post.DELETED_FIELD),postSearchRequest.getDeleted()));
        }
        if (Objects.nonNull(postSearchRequest.getKeyword())){
            Predicate keywordPredicate = criteriaBuilder.or(
                    criteriaBuilder.like(root.get(Post.TITLE_NAME_FIELD),  "%" + postSearchRequest.getKeyword () + "%"),
                    criteriaBuilder.like(root.get(Post.CONTENT_NAME_FIELD),  "%" + postSearchRequest.getKeyword () + "%")
            );
            predicates.add(keywordPredicate);
        }

        sort(root,criteriaBuilder,query);
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
    private void sort(Root<Post> root,CriteriaBuilder criteriaBuilder,CriteriaQuery<?> query){
        if (Objects.nonNull(postSearchRequest.getSortField())) {
            switch (postSearchRequest.getSortField()){
                case TITLE -> query.orderBy(criteriaBuilder.desc(root.get(Post.TITLE_NAME_FIELD)));
                case CONTENT -> query.orderBy(criteriaBuilder.desc(root.get(Post.CONTENT_NAME_FIELD)));
                case LIKES -> query.orderBy(criteriaBuilder.desc(root.get(Post.LIKES_NAME_FIELD)));
                default ->  query.orderBy(criteriaBuilder.desc(root.get(Post.ID_FIELD)));
            }
        }else{
            query.orderBy(criteriaBuilder.desc(root.get(Post.ID_FIELD)));
        }
    }
}
