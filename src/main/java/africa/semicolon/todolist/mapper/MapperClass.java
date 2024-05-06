package africa.semicolon.todolist.mapper;

import africa.semicolon.todolist.data.model.User;
import africa.semicolon.todolist.dtos.SignUpRequest;
import africa.semicolon.todolist.responses.SignUpResponse;

public class MapperClass {

    public static User signUpMapper(SignUpRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setUserId(request.getUserId());
        return user;
    }

    public static SignUpResponse signUpResponseMapper(User user){
        SignUpResponse response = new SignUpResponse();
        response.setUsername(user.getUsername());
        response.setUserId(user.getUserId());
        response.setMessage("you successfully signed up");
        return response;
    }
}
