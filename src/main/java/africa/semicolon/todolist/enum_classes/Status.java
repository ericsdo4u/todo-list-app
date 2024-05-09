package africa.semicolon.todolist.enum_classes;

import africa.semicolon.todolist.exceptions.TodoListException;

public enum Status {
    START_TASK,
    IN_PROGRESS,
    TASK_COMPLETED;

    public static class TaskAlreadyExistException extends TodoListException {
        public TaskAlreadyExistException(String message) {
            super(message);
        }
    }
}
