package com.iuri.spring_api.service;

import com.iuri.spring_api.dto.UserRequest;
import com.iuri.spring_api.dto.UserResponse;
import com.iuri.spring_api.model.User;
import com.iuri.spring_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse save(UserRequest request) {
        return UserResponse.convert(userRepository.save(User.convert(request)));
    }

    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream().map(UserResponse::convert).toList();
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
