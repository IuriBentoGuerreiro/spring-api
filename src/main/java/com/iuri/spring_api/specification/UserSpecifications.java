package com.iuri.spring_api.specification;

import com.iuri.spring_api.model.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {

    public static Specification<User> filterByName(String name) {
        return (root, query, cb) -> {
            if (name == null || name.isBlank()) {
                return cb.conjunction();
            }

            String searchTerm = "%" + name.toLowerCase() + "%";

            return cb.like(
                    cb.function("unaccent", String.class, cb.lower(root.get("name"))),
                    cb.function("unaccent", String.class, cb.literal(searchTerm))
            );
        };
    }
}
