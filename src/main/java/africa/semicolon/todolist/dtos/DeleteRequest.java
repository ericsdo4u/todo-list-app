package africa.semicolon.todolist.dtos;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class DeleteRequest {
    //private String username;
    @Id
    private String taskId;
}
