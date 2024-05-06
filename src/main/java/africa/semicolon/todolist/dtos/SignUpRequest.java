package africa.semicolon.todolist.dtos;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class SignUpRequest {
    private String username;
    private String password;
    private String userId;
}
