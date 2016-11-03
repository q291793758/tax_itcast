package cn.itcast.core.exception;

public class SysException extends Exception {
    private String errorMsg;

    public SysException() {
    }

    public SysException(String message) {
        super(message);
        errorMsg = message;
    }

    public SysException(String message, Throwable cause) {
        super(message, cause);
        errorMsg = message;

    }

    public SysException(Throwable cause) {
        super(cause);
    }
}
