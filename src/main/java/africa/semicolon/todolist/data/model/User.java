package africa.semicolon.todolist.data.model;

import africa.semicolon.todolist.enum_classes.Priority;
import africa.semicolon.todolist.enum_classes.Status;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@Document("user")
public class User {
    private String username;
    private String password;
    @Id
    private String userId;
    private boolean lock;
    private List<Task> task;
    private Priority prioritiseTask;
    private Status taskStatus;
}
