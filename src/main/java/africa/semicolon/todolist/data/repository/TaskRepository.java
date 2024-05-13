package africa.semicolon.todolist.data.repository;

import africa.semicolon.todolist.data.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

    Optional<Task> findTaskByTaskId(String taskId);

    Optional<Task> findByUsername(String username);

    List<Task> findTaskByUsername(String username);

    Optional<Task> findByTaskName(String taskName);

}
