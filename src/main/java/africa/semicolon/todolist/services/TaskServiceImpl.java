package africa.semicolon.todolist.services;

import africa.semicolon.todolist.data.model.Task;
import africa.semicolon.todolist.data.model.User;
import africa.semicolon.todolist.data.repository.TaskRepository;
import africa.semicolon.todolist.data.repository.UserRepository;
import africa.semicolon.todolist.dtos.CreateTaskRequest;
import africa.semicolon.todolist.exceptions.TaskAlreadyWithThisTaskNameExistException;
import africa.semicolon.todolist.exceptions.TaskDoesNotExistException;
import africa.semicolon.todolist.exceptions.UserNotFoundExcetion;
import africa.semicolon.todolist.responses.CreateTaskResponse;
import africa.semicolon.todolist.responses.DeleteResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static africa.semicolon.todolist.mapper.MapperClass.*;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;


    @Override
    public CreateTaskResponse createTask(CreateTaskRequest request) {
        userService.checkUser(request.getUsername());
        validateTask(request.getTaskName());
        Task task = mapCreateTask(request);
        taskRepository.save(task);
        return mapTaskResponse(task);
    }

    @Override
    public CreateTaskResponse editTask(CreateTaskRequest request) {
        Optional<Task> foundTask = taskRepository.findTaskByTaskId(request.getTaskId());
        if (foundTask.isEmpty()) {
            throw new TaskDoesNotExistException("task not found");
        }
        Task existingTask = foundTask.get();
        existingTask.setTaskId(request.getTaskId());
        existingTask.setUsername(request.getUsername());
        existingTask.setTaskName(request.getTaskName());
        existingTask.setTaskDetail(request.getTaskDetail());
        existingTask.setDuration(request.getDuration());
        Task editedTask = taskRepository.save(existingTask);

        return mapTaskResponse(editedTask);
    }

    @Override
    public long getNumberOfTaskCreated() {
        return taskRepository.count();
    }

    @Override
    public void validateTask(String taskName) {
        Optional<Task> foundTask = taskRepository.findByTaskName(taskName);
        if (foundTask.isPresent()) {
            throw new TaskAlreadyWithThisTaskNameExistException("task with this task name exist already, please edit task");
        }
    }

    @Override
    public List<Task> findListOfTaskByUsername(String userName) {
        List<Task> foundTask = taskRepository.findTaskByUsername(userName);
        if (foundTask.isEmpty()) {
            throw new TaskDoesNotExistException("no task with this username");
        }
        return foundTask;
    }

    @Override
    public List<Task> findAllTask() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> findTaskByTaskName(String taskName) {
        Optional<Task> foundTask = taskRepository.findByTaskName(taskName);
        if (foundTask.isEmpty()) {
            throw new TaskDoesNotExistException("task not found");
        }
        //taskRepository.save(foundTask.get());
        return foundTask;
    }

    @Override
    public DeleteResponse deleteTask(String taskId) {
        Optional<Task> foundTask = taskRepository.findTaskByTaskId(taskId);
        if (foundTask.isEmpty()) {
            throw new TaskDoesNotExistException("task not found");
        }
        taskRepository.delete(foundTask.get());
        DeleteResponse response = new DeleteResponse();
        response.setMessage("task deleted");
        return response;
    }

    @Override
    public Task findTaskByUsername(String username) {
        Task foundTask = (taskRepository.findTasksByUsername(username));
        if (foundTask == null) {
            throw new TaskDoesNotExistException("task not found");
        }
        taskRepository.save(foundTask);
        return foundTask;
    }

    public Task findTaskByTaskId(String taskId) {
        Task foundTask = (taskRepository.findByTaskId(taskId));
        if (foundTask == null) {
            throw new UserNotFoundExcetion("task not found");
        }
        taskRepository.save(foundTask);
        return foundTask;
    }


    @Override
    public void assignTask(String taskId, String taskId1) {
        Task foundTask = findTaskByTaskId(taskId);
        Task foundTask1 = findTaskByTaskId(taskId1);

        if (foundTask != null && foundTask1 != null) {
            taskRepository.delete(foundTask);
            foundTask1.setAssignedStatus("Assigned");
            taskRepository.save(foundTask1);
        }
        throw new TaskDoesNotExistException("one or either two of the task with the id not found");
    }

    @Override
    public void shareTask(String taskId, String assigneeId) {
        Task foundTask = findTaskByTaskId(taskId);
        User assignee = userRepository.findUserByUserId(assigneeId);

        if (foundTask != null && assignee != null) {
            foundTask.setAssignee(assignee);
            taskRepository.save(foundTask);
        }
        throw new TaskDoesNotExistException("taskId or userId not found");
    }
}

