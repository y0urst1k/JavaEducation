package exceptions;

import java.io.IOException;

public class InvalidLineException extends IOException {
    private final int lineNumber;

    public InvalidLineException(int lineNumber, String message, Throwable cause) {
        super(message, cause);
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}
