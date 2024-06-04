package com.lendops.lendops.service;

import com.lendops.lendops.dto.UserRequestDTO;
import com.lendops.lendops.orm.User;
import com.lendops.lendops.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User registerUser(UserRequestDTO userDTO) {
        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(userDTO.password());

            User user = User.builder()
                    .name(userDTO.name())
                    .email(userDTO.email())
                    .username(userDTO.username())
                    .isAdmin(userDTO.isAdmin())
                    .medias(Collections.emptyList())
                    .password(hashedPassword)
                    .build();
            return userRepository.save(user);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new DataAccessException("Not possible to create user", e) {
            };
        }
    }

    public List<User> getAllUsers() {
        Iterable<User> userIterable = userRepository.findAll();
        return StreamSupport.stream(userIterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    }

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public void deleteUserById(Long userId) {
        try {
            if (userRepository.existsById(userId)) {
                userRepository.deleteById(userId);
            } else {
                throw new RuntimeException("User not found with id: " + userId);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new DataAccessException("Failed to delete user", e) {
            };
        }
    }

    public Long getUserCount() {
        try {
            return userRepository.count();
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new DataAccessException("Failed to get number os users", e) {
            };
        }
    }
}