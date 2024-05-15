package africa.semicolon.todolist.services;

import africa.semicolon.todolist.data.model.User;
import africa.semicolon.todolist.dtos.LoginRequest;
import africa.semicolon.todolist.dtos.SignUpRequest;
import africa.semicolon.todolist.responses.LoginResponse;
import africa.semicolon.todolist.responses.SignUpResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    SignUpResponse signUp(SignUpRequest request);

    long getNumberOfRegisterUsers();

    LoginResponse login(LoginRequest request);

    void checkUser(String username);

    Optional<User> findByUsername(String username);
}
