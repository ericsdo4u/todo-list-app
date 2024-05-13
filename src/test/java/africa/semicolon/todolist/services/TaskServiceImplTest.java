package africa.semicolon.todolist.services;

import africa.semicolon.todolist.data.model.Task;
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


import java.util.List;
import java.util.Optional;
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

        CreateTaskResponse createTaskResponse = taskService.createTask(request1);
        System.out.println(createTaskResponse.getTaskName());
        System.out.println(createTaskResponse.getTaskDetail());
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
        CreateTaskResponse createTaskResponse = taskService.createTask(request1);
        System.out.println(createTaskResponse);


        request1.setUsername("dond");
        request1.setTaskName("read");
        request1.setTaskDetail("the book details has been changed");
        CreateTaskResponse response = taskService.editTask(request1);
        System.out.println(response);
        assertEquals("the book details has been changed", response.getTaskDetail());

    }
    @Test
    public void testFindAllTask() {

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
        CreateTaskResponse createTaskResponse = taskService.createTask(request1);
        System.out.println(createTaskResponse);


        CreateTaskRequest request0 = new CreateTaskRequest();
        request0.setUsername("dond");
        request0.setTaskName("readd");
        request0.setTaskDetail("js text book chapter 7");
        CreateTaskResponse createTaskResponse1 = taskService.createTask(request0);
        System.out.println(createTaskResponse1);
        List<Task> getTask = taskService.findAllTask();
        assertEquals(getTask, taskService.findAllTask());
    }

    @Test
    public void testFindAllTaskByUsername() {

        SignUpRequest request = new SignUpRequest();
        request.setUsername("dondd");
        request.setPassword("1234");
        userService.signUp(request);

        LoginRequest request2 = new LoginRequest();
        request2.setUsername("dondd");
        request2.setPassword("1234");
        userService.login(request2);

        CreateTaskRequest request1 = new CreateTaskRequest();
        request1.setUsername("dondd");
        request1.setTaskName("read");
        request1.setTaskDetail("js text book chapter 7");
        CreateTaskResponse createTaskResponse = taskService.createTask(request1);
        System.out.println(createTaskResponse);


        CreateTaskRequest request0 = new CreateTaskRequest();
        request0.setUsername("dondd");
        request0.setTaskName("readd");
        request0.setTaskDetail("js text book chapter 7");
        CreateTaskResponse createTaskResponse1 = taskService.createTask(request0);
        System.out.println(createTaskResponse1);
        Optional<Task> getTask = taskService.findTaskByTaskName("readd");
        assertTrue(getTask.isPresent());
        assertEquals("readd", getTask.get().getTaskName());
    }
}