package com.lendops.lendops.service;

import com.lendops.lendops.orm.User;
import com.lendops.lendops.orm.UserAccess;
import com.lendops.lendops.repository.UserAccessRepository;
import com.lendops.lendops.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class UserAccessService {

    private final UserRepository userRepository;
    private final UserAccessRepository userAccessRepository;

    public User login(String username, String password) {
        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            User user = userRepository.findByUsername(username)
                    .orElse(null);
            if (user == null) {
                return null;
            }
            String storedPassword = user.getPassword();
            // Verifica se a senha fornecida corresponde à senha armazenada
            if (passwordEncoder.matches(password, storedPassword)) {
                // Senha correta, login bem-sucedido
                UserAccess userAccess = UserAccess.builder()
                        .accessDate(LocalDateTime.now())
                        .user(user)
                        .build();
                userAccessRepository.save(userAccess);
                return user;
            } else {
                // Senha incorreta, login falha
                return null;
            }
        } catch (
                DataAccessException e) {
            e.printStackTrace();
            throw new DataAccessException("Not possible to login", e) {
            };
        }
    }

    public List<UserAccess> getAllUsersAccess() {
        Iterable<UserAccess> userIterable = userAccessRepository.findAll();
        return StreamSupport.stream(userIterable.spliterator(), false)
                .collect(Collectors.toList());
    }


    public Long userAccessCount() {
        try {
            return userAccessRepository.count();
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new DataAccessException("Failed to find access", e) {
            };
        }
    }
}