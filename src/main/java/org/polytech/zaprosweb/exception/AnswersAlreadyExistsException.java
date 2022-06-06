package org.polytech.zaprosweb.exception;

public class AnswersAlreadyExistsException extends Exception {
    public AnswersAlreadyExistsException() {
        super();
    }

    public AnswersAlreadyExistsException(String message) {
        super(message);
    }

    public AnswersAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public AnswersAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
