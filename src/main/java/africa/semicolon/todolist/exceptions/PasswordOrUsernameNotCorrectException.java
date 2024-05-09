package africa.semicolon.todolist.exceptions;

public class PasswordOrUsernameNotCorrectException extends TodoListException {
    public PasswordOrUsernameNotCorrectException(String message) {
        super(message);
    }
}
