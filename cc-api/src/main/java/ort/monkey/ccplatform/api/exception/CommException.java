package ort.monkey.ccplatform.api.exception;

/**
 * CommException
 *
 * @author cc
 * @since 2024/5/31 10:03
 */
public class CommException extends Exception {
    public CommException() {
        super();
    }

    public CommException(String message) {
        super(message);
    }

    public CommException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommException(Throwable cause) {
        super(cause);
    }

    protected CommException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
