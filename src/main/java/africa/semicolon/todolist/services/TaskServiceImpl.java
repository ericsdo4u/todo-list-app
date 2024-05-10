package africa.semicolon.todolist.services;

import africa.semicolon.todolist.data.model.Task;
import africa.semicolon.todolist.data.model.User;
import africa.semicolon.todolist.data.repository.TaskRepository;
import africa.semicolon.todolist.data.repository.UserRepository;
import africa.semicolon.todolist.dtos.CreateTaskRequest;
import africa.semicolon.todolist.enum_classes.Status;
import africa.semicolon.todolist.exceptions.TaskDoesNotExistException;
import africa.semicolon.todolist.exceptions.UserNotFoundExcetion;
import africa.semicolon.todolist.responses.CreateTaskResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static africa.semicolon.todolist.mapper.MapperClass.*;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Override
    public CreateTaskResponse createTask(CreateTaskRequest request) {
        Optional<User> foundUser = userRepository.findByUsername(request.getUsername());
        if (foundUser.isEmpty()){
            throw new UserNotFoundExcetion("user not found");
        }
        checkState(foundUser.get());
        validateTask(request.getTaskName());
        Task task = mapCreateTask(request);
        taskRepository.save(task);
        return mapTaskResponse(task);
    }
    @Override
    public CreateTaskResponse editTask(CreateTaskRequest request){
        Optional<Task> foundTask = taskRepository.findByTaskName(request.getTaskName());
        if (foundTask.isEmpty()) {
            throw new TaskDoesNotExistException("task not found");
        }
        Task existingTask = foundTask.get();
       // existingTask.setTaskId(request.getTaskId());
        existingTask.setUsername(request.getUsername());
        existingTask.setTaskName(request.getTaskName());
        existingTask.setTaskDetail(request.getTaskDetail());
       // existingTask.setDuration(request.getDuration());
        Task editedTask = taskRepository.save(existingTask);
        return mapTaskResponse(editedTask);
    }
    @Override
    public long getNumberOfTaskCreated() {
        return taskRepository.count();
    }

    @Override
    public void validateTask(String taskName){
        Optional<Task> foundPost = taskRepository.findByTaskName(taskName);
        if (foundPost.isPresent()){
            throw new Status.TaskAlreadyExistException("task with this id exist already, please login");
        }
    }
    @Override
    public List<Task> findListOfTask(String userName) {
        List<Task> foundTask = taskRepository.findTaskByUsername(userName);
        if (foundTask.isEmpty()){
            throw new TaskDoesNotExistException("no task with this username");
        }
        return foundTask;
    }
    @Override
    public List<Task> findAllTask() {
        return taskRepository.findAll();
    }
}
