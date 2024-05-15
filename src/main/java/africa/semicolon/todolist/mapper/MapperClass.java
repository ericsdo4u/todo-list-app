package africa.semicolon.todolist.mapper;

import africa.semicolon.todolist.data.model.Task;
import africa.semicolon.todolist.data.model.User;
import africa.semicolon.todolist.dtos.CreateTaskRequest;
import africa.semicolon.todolist.dtos.SignUpRequest;
import africa.semicolon.todolist.exceptions.UserAccountIsLockException;
import africa.semicolon.todolist.responses.CreateTaskResponse;
import africa.semicolon.todolist.responses.SignUpResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class MapperClass {

    public static User signUpMapper(SignUpRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        //user.setUserId(request.getUserId());
        return user;
    }
    public static SignUpResponse signUpResponseMapper(User user){
        SignUpResponse response = new SignUpResponse();
        response.setUserId(user.getUserId());
        response.setMessage("you successfully signed up");
        return response;
    }
    public static Task mapCreateTask(CreateTaskRequest request){
        Task task = new Task();
        //task.setTaskId(request.getTaskId());
        task.setUsername(request.getUsername());
        task.setTaskName(request.getTaskName());
        task.setTaskDetail(request.getTaskDetail());
        task.setDuration(request.getDuration());
//        task.setStartTime(LocalDateTime.parse(DateTimeFormatter.ofPattern("dd/MM/yyyy 'at' HH:mm:ss a").format(request.getStartTime())));
//        task.setStopTime(LocalDateTime.parse(DateTimeFormatter.ofPattern("dd/MM/yyyy 'at' HH:mm:ss a").format(request.getStopTime())));
        return task;
    }
    public static CreateTaskResponse mapTaskResponse(Task task) {
        CreateTaskResponse response = new CreateTaskResponse();
        response.setTaskId(task.getTaskId());
        response.setTaskName(task.getTaskName());
        response.setUsername(task.getUsername());
        response.setTaskDetail(task.getTaskDetail());
        response.setStartTime(DateTimeFormatter.ofPattern("dd/MM/yyyy 'at' HH:mm:ss a").format(task.getStartTime()));
        response.setStopTime(DateTimeFormatter.ofPattern("dd/MM/yyyy 'at' HH:mm:ss a").format(task.getStopTime()));
        return response;
    }

    public static void checkState(User user){
        if (user.isLock()){
            throw new UserAccountIsLockException("account is locked, please login to access your account");
        }
    }
    public static boolean isPasswordIncorrect(User user, String password){
        return  (!user.getPassword().equals(password));
    }
}
