package africa.semicolon.todolist.controller;

import africa.semicolon.todolist.dtos.SignUpRequest;
import africa.semicolon.todolist.exceptions.TodoListException;
import africa.semicolon.todolist.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
public class Controller {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest request){
        try {
            var result = userService.signUp(request);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }catch (TodoListException exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), BAD_REQUEST);
        }
    }
}
