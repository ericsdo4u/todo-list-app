package africa.semicolon.todolist.exceptions;

import africa.semicolon.todolist.exceptions.TodoListException;

public class UserAlreadyExistException extends TodoListException {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
