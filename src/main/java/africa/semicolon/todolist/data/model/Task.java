package africa.semicolon.todolist.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Document("task")
public class Task {
    @Id
    private String taskId;
    private String username;
    private String taskName;
    private String taskDetail;
    private LocalDateTime startTime = LocalDateTime.now();
    private LocalDateTime stopTime = LocalDateTime.now();
    private String duration;
}
