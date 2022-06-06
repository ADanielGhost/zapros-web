package org.polytech.zaprosweb.exception;

public class QesAlreadyExistsException extends Exception {
    public QesAlreadyExistsException() {
        super();
    }

    public QesAlreadyExistsException(String message) {
        super(message);
    }

    public QesAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public QesAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
