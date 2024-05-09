package africa.semicolon.todolist.services;

import africa.semicolon.todolist.data.model.Task;
import africa.semicolon.todolist.dtos.CreateTaskRequest;
import africa.semicolon.todolist.responses.CreateTaskResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {
   CreateTaskResponse createTask(CreateTaskRequest request);

    CreateTaskResponse editTask(CreateTaskRequest request);

    long getNumberOfTaskCreated();

    void validateTask(String taskId);

    List<Task> findListOfTask(String task);
}
