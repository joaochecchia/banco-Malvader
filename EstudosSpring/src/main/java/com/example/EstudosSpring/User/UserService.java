package com.example.EstudosSpring.User;

import com.example.EstudosSpring.Mapper.UserMapper;
import com.example.EstudosSpring.Response.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service // Adicione esta anotação
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<String> save(RegisterRequest request) {
        if (userRepository.findUserByUsername(request.username()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Usuario com esse nome ja cadastrado");
        }

        UserModel newUser = userMapper.map(request);
        newUser.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(newUser);

        return ResponseEntity.ok("Usuário criado com sucesso!");
    }
}
