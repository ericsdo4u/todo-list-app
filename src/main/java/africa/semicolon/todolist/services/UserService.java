package africa.semicolon.todolist.services;

import africa.semicolon.todolist.dtos.SignUpRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    SignUpResponse signUp(SignUpRequest request);

    long getNumberOfRegisterUsers();

}
