package africa.semicolon.todolist.data.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Document
public class Task {
    private String taskId;
    private String taskName;
    private LocalDateTime startTime;
    private LocalDateTime stopTime;
    private String duration;
    private String priority;
}
