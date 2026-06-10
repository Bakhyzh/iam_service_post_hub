package com.post_hub.iam_service.repositories.criteria;


import com.post_hub.iam_service.model.entity.User;
import com.post_hub.iam_service.model.request.user.UserSearchRequest;
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
public class UserSearchCriteria implements Specification<User> {
    private UserSearchRequest userSearchRequest;

    @Override
    public Predicate toPredicate(@NonNull Root<User> root,
                                 CriteriaQuery<?> query,
                                 @NonNull CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(userSearchRequest.getUsername())){
            predicates.add(criteriaBuilder.like(root.get(User.USERNAME_NAME_FIELD),"%" + userSearchRequest.getUsername() + "%"));
        }
        if (Objects.nonNull(userSearchRequest.getEmail())){
            predicates.add(criteriaBuilder.like(root.get(User.EMAIL_NAME_FILED),"%" + userSearchRequest.getEmail() + "%"));
        }
        if (Objects.nonNull(userSearchRequest.getDeleted())){
            predicates.add(criteriaBuilder.equal(root.get(User.DELETED_FIELD),userSearchRequest.getDeleted()));
        }
        if (Objects.nonNull(userSearchRequest.getKeyword())){
            Predicate keywordPredicate = criteriaBuilder.or(
                    criteriaBuilder.like(root.get(User.USERNAME_NAME_FIELD),  "%" + userSearchRequest.getKeyword () + "%"),
                    criteriaBuilder.like(root.get(User.EMAIL_NAME_FILED),  "%" + userSearchRequest.getKeyword () + "%")
            );
            predicates.add(keywordPredicate);
        }

        sort(root,criteriaBuilder,query);
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
    private void sort(Root<User> root,CriteriaBuilder criteriaBuilder,CriteriaQuery<?> query){
        if (Objects.nonNull(userSearchRequest.getSortField())) {
            switch (userSearchRequest.getSortField()){
                case USERNAME -> query.orderBy(criteriaBuilder.desc(root.get(User.USERNAME_NAME_FIELD)));
                case EMAIL -> query.orderBy(criteriaBuilder.desc(root.get(User.EMAIL_NAME_FILED)));

                default ->  query.orderBy(criteriaBuilder.desc(root.get(User.ID_FIELD)));
            }
        }else{
            query.orderBy(criteriaBuilder.desc(root.get(User.ID_FIELD)));
        }
    }
}
