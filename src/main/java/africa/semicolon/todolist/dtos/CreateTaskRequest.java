package africa.semicolon.todolist.dtos;

import africa.semicolon.todolist.enum_classes.Priority;
import africa.semicolon.todolist.enum_classes.Status;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Data
@Document
public class CreateTaskRequest {

    private String taskId;
    private String username;
    private String taskName;
    private String taskDetail;
    private String duration;
    private Priority prioritiseTask;
    private Status taskStatus;
    private LocalDateTime startTime = LocalDateTime.now();
    private LocalDateTime stopTime = LocalDateTime.now();
}
