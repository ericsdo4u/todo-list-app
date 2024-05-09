package africa.semicolon.todolist.services;

import africa.semicolon.todolist.data.repository.TaskRepository;
import africa.semicolon.todolist.data.repository.UserRepository;
import africa.semicolon.todolist.dtos.CreateTaskRequest;
import africa.semicolon.todolist.dtos.LoginRequest;
import africa.semicolon.todolist.dtos.SignUpRequest;
import africa.semicolon.todolist.responses.CreateTaskResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskServiceImplTest {
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;
    @Autowired
    private TaskRepository repository;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        repository.deleteAll();
        userRepository.deleteAll();
    }
    @Test
    public void testThatTaskCnaBeCreated(){

        SignUpRequest request = new SignUpRequest();
        request.setUsername("donaldddd");
        request.setPassword("1234");
        userService.signUp(request);

        LoginRequest request2 = new LoginRequest();
        request2.setUsername("donaldddd");
        request2.setPassword("1234");
        userService.login(request2);

        CreateTaskRequest request1 = new CreateTaskRequest();
        request1.setUsername("donaldddd");
        request1.setTaskName("read");
        request1.setTaskDetail("js text book chapter 7");
        taskService.createTask(request1);
        assertEquals(1, taskService.getNumberOfTaskCreated());
    }
    @Test
    public void testThatTaskCanBeEdited(){

        SignUpRequest request = new SignUpRequest();
        request.setUsername("dond");
        request.setPassword("1234");
        userService.signUp(request);

        LoginRequest request2 = new LoginRequest();
        request2.setUsername("dond");
        request2.setPassword("1234");
        userService.login(request2);

        CreateTaskRequest request1 = new CreateTaskRequest();
        request1.setUsername("dond");
        request1.setTaskName("read");
        request1.setTaskDetail("js text book chapter 7");
        taskService.createTask(request1);

        request1.setUsername("dond");
        request1.setTaskName("read");
        request1.setTaskDetail("the book details has been changed");
        CreateTaskResponse response = taskService.editTask(request1);
        assertEquals("read up", response.getTaskName());

    }
}