package com.iuri.spring_api.model;

import com.iuri.spring_api.dto.UserRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "users")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;
    @Email
    @Column(name = "email", nullable = false)
    private String email;

    public static User convert (UserRequest request){
       return  User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .build();
    }
}
