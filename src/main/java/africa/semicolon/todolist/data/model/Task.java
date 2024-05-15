package africa.semicolon.todolist.data.model;

import africa.semicolon.todolist.enum_classes.Priority;
import africa.semicolon.todolist.enum_classes.Status;
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
    private String duration;
    private Priority prioritiseTask;
    private Status taskStatus;
    private String assignedStatus;
    private User assignee;
    private LocalDateTime startTime = LocalDateTime.now();
    private LocalDateTime stopTime = LocalDateTime.now();
}
