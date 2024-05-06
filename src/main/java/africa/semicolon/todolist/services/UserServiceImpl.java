package africa.semicolon.todolist.services;

import africa.semicolon.todolist.data.model.User;
import africa.semicolon.todolist.data.repository.UserRepository;
import africa.semicolon.todolist.dtos.SignUpRequest;
import africa.semicolon.todolist.responses.SignUpResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static africa.semicolon.todolist.mapper.MapperClass.signUpMapper;
import static africa.semicolon.todolist.mapper.MapperClass.signUpResponseMapper;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;
    @Override
    public SignUpResponse signUp(SignUpRequest request) {
        User user = signUpMapper(request);
        User newUser = repository.save(user);
        return signUpResponseMapper(newUser);
    }

    @Override
    public long getNumberOfRegisterUsers() {
        return repository.count();
    }
}
