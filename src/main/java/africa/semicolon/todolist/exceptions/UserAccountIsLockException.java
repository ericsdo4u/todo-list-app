package africa.semicolon.todolist.exceptions;

import africa.semicolon.todolist.exceptions.TodoListException;

public class UserAccountIsLockException extends TodoListException {
    public UserAccountIsLockException(String massage) {
        super(massage);
    }
}
