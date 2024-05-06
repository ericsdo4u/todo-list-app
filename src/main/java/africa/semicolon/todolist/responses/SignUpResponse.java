package africa.semicolon.todolist.responses;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class SignUpResponse {
    private String username;
    private String password;
    private String userId;
    private String message;
}
