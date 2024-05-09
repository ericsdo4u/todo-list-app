package africa.semicolon.todolist.exceptions;


public class TaskDoesNotExistException extends TodoListException {
    public TaskDoesNotExistException(String message) {
        super(message);
    }
}
