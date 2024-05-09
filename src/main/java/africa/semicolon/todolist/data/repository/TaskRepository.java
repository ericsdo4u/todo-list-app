package africa.semicolon.todolist.data.repository;

import africa.semicolon.todolist.data.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

    Optional<Task> findByUsername(String username);

}
