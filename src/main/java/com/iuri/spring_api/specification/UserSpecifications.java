package com.iuri.spring_api.specification;

import com.iuri.spring_api.filter.FilterName;
import com.iuri.spring_api.model.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {

    public static Specification<User> filterByName(FilterName filter) {
        return (root, query, cb) -> {
            if (filter.name() == null || filter.name().isBlank()) {
                return cb.conjunction();
            }

            String searchTerm = "%" + filter.name().toLowerCase() + "%";

            return cb.like(
                    cb.function("unaccent", String.class, cb.lower(root.get("name"))),
                    cb.function("unaccent", String.class, cb.literal(searchTerm))
            );
        };
    }
}
