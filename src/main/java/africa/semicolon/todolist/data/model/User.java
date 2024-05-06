package africa.semicolon.todolist.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Document("user")
public class User {
    private String username;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
    @Id
    private String userId;
    private Task task;
}
