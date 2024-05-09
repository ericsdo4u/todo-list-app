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
        Optional<Task> foundTask = taskRepository.findByUsername(request.getUsername());
        if (foundTask.isEmpty()) {
            throw new TaskDoesNotExistException("task not found");
        }
        validateTask(request.getTaskName());
        Task existingTask = foundTask.get();
        existingTask.setTaskId(request.getTaskId());
        existingTask.setUsername(request.getUsername());
        existingTask.setTaskName(request.getTaskName());
        existingTask.setTaskDetail(request.getTaskDetail());
        existingTask.setDuration(request.getDuration());
        taskRepository.save(existingTask);
        return mapTaskResponse(existingTask);
    }
    @Override
    public long getNumberOfTaskCreated() {
        return taskRepository.count();
    }

    @Override
    public void validateTask(String taskId){
        Optional<Task> foundPost = taskRepository.findById(taskId);
        if (foundPost.isPresent()){
            throw new Status.TaskAlreadyExistException("task with this id exist already, please login");
        }
    }

    @Override
    public List<Task> findListOfTask(String task) {
        return null;
    }
}
