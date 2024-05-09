package africa.semicolon.todolist.services;

import africa.semicolon.todolist.data.model.User;
import africa.semicolon.todolist.data.repository.UserRepository;
import africa.semicolon.todolist.dtos.LoginRequest;
import africa.semicolon.todolist.dtos.SignUpRequest;
import africa.semicolon.todolist.exceptions.PasswordOrUsernameCannotBeEmptyException;
import africa.semicolon.todolist.exceptions.PasswordOrUsernameNotCorrectException;
import africa.semicolon.todolist.exceptions.UserAlreadyExistException;
import africa.semicolon.todolist.exceptions.UserNotFoundExcetion;
import africa.semicolon.todolist.responses.LoginResponse;
import africa.semicolon.todolist.responses.SignUpResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static africa.semicolon.todolist.mapper.MapperClass.*;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public SignUpResponse signUp(SignUpRequest request) {
        if (request.getUsername().toLowerCase().toLowerCase().isBlank() || request.getPassword().toLowerCase().isBlank()) {
            throw new PasswordOrUsernameCannotBeEmptyException("username or password cannot be empty");
        }
        validateUser(request.getUsername().toLowerCase());
        User user = signUpMapper(request);
        User newUser = userRepository.save(user);
        return signUpResponseMapper(newUser);
    }
    @Override
    public LoginResponse login(LoginRequest request) {
        checkUser(request.getUsername());
        User foundUser = userRepository.findUserBy(request.getUsername());
        if (isPasswordIncorrect(foundUser, request.getPassword())) {
            throw new PasswordOrUsernameNotCorrectException("username or password not correct");
        }
        foundUser.setLock(false);
        userRepository.save(foundUser);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setMessage("login successful");
        return loginResponse;
    }
    @Override
    public long getNumberOfRegisterUsers() {
        return userRepository.count();
    }
    @Override
    public void checkUser(String username){
        Optional<User> foundUser = userRepository.findByUsername(username);
        if (foundUser.isEmpty()) {
            throw new UserNotFoundExcetion("user not found");
        }
    }
    public void validateUser(String username){
        Optional<User> foundUser = userRepository.findByUsername(username);
        if (foundUser.isPresent()){
            throw new UserAlreadyExistException("user already registered, please login");
        }
    }
}
