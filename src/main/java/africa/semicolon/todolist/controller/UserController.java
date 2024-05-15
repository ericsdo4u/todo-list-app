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
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/find-all-task")
    public ResponseEntity<?> findAll(){
        try {
            var result = taskService.findAllTask();
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }catch (TodoListException e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
    @PostMapping("/create-task")
    public ResponseEntity<?> createTask(@RequestBody CreateTaskRequest request){
        try {
            var result = taskService.createTask(request);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }catch (TodoListException exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), BAD_REQUEST);
        }
    }
    @GetMapping("/find-task-by-username/{task-name}")
    public ResponseEntity<?> findTaskByTaskName(@PathVariable("task-name") String taskName){
        try {
            var result = taskService.findTaskByTaskName(taskName);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }catch (TodoListException e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete-task/{task-id}")
    public ResponseEntity<?> deleteTask(@PathVariable("task-id") String taskId){
        try {
            var result = taskService.deleteTask(taskId);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }catch (TodoListException exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), BAD_REQUEST);
        }
    }
    @GetMapping("/find-by-username/{username}")
    public ResponseEntity<?> findByUsername(@PathVariable("username") String username){
        try {
            var result = taskService.findListOfTaskByUsername(username);
            return new  ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }catch (TodoListException e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
}
