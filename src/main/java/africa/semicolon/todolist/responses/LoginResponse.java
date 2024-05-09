package africa.semicolon.todolist.responses;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class LoginResponse {
    private String message;
}
