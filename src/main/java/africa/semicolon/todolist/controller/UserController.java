package africa.semicolon.todolist.controller;

import africa.semicolon.todolist.dtos.CreateTaskRequest;
import africa.semicolon.todolist.dtos.LoginRequest;
import africa.semicolon.todolist.dtos.SignUpRequest;
import africa.semicolon.todolist.exceptions.TodoListException;
import africa.semicolon.todolist.responses.ApiResponse;
import africa.semicolon.todolist.services.TaskService;
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
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest request){
        try {
            var result = userService.signUp(request);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }catch (TodoListException exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        try {
            var result = userService.login(request);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }catch (TodoListException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/edit-task")
    public ResponseEntity<?> editTask(@RequestBody CreateTaskRequest request){
        try {
            var result = taskService.editTask(request);
            return new ResponseEntity<>(new ApiResponse(true, result),CREATED);
        }catch (TodoListException e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
}
