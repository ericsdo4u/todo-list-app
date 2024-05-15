package africa.semicolon.todolist.exceptions;

import africa.semicolon.todolist.exceptions.TodoListException;

public class TaskAlreadyWithThisTaskNameExistException extends TodoListException {
    public TaskAlreadyWithThisTaskNameExistException(String message) {
        super(message);
    }
}
