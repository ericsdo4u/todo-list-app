package africa.semicolon.todolist.exceptions;

public class PasswordOrUsernameCannotBeEmptyException extends TodoListException {
    public PasswordOrUsernameCannotBeEmptyException(String message) {
        super(message);
    }
}
