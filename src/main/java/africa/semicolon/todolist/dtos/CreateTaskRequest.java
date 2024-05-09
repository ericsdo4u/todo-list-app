package africa.semicolon.todolist.dtos;

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
    private String startTime;
    private String stopTime;
    private String duration;
    private String priority;
}
