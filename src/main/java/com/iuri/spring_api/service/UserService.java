package com.iuri.spring_api.service;

import com.iuri.spring_api.dto.UserRequest;
import com.iuri.spring_api.dto.UserResponse;
import com.iuri.spring_api.filter.FilterName;
import com.iuri.spring_api.model.User;
import com.iuri.spring_api.repository.UserRepository;
import com.iuri.spring_api.specification.UserSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse save(UserRequest request) {
        return UserResponse.convert(userRepository.save(User.convert(request)));
    }

    @Transactional(readOnly = true)
    public Page<UserResponse> searchName(FilterName filter, Pageable pageable) {
        Page<User> userPage = userRepository.findAll(
                UserSpecifications.filterByName((filter)), pageable);

        return userPage.map(UserResponse::convert);
    }

    @Transactional(readOnly = true)
    public UserResponse findById(Integer id) {
        return UserResponse.convert(userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuário não Encontrado")
        ));
    }

    @Transactional
    public UserResponse update(Integer id, UserRequest request) {
        User userSaved = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuário não encontrado")
        );

        BeanUtils.copyProperties(request, userSaved, "id");

        return UserResponse.convert(userRepository.save(userSaved));
    }

    @Transactional
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
