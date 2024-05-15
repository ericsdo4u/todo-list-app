package africa.semicolon.todolist.responses;

import africa.semicolon.todolist.enum_classes.Priority;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class CreateTaskResponse {
    @Id
    private String taskId;
    private String username;
    private String taskName;
    private String taskDetail;
    private String startTime;
    private String stopTime;
   // private String duration;
//    private Priority priority;
//    private String message;
}
