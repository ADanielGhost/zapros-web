package org.polytech.zaprosweb.exception;

public class AlternativePackageNotFoundException extends Exception {
    public AlternativePackageNotFoundException() {
        super();
    }

    public AlternativePackageNotFoundException(String message) {
        super(message);
    }

    public AlternativePackageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlternativePackageNotFoundException(Throwable cause) {
        super(cause);
    }
}
