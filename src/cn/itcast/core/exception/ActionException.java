package cn.itcast.core.exception;

public class ActionException extends SysException{
    public ActionException() {
        super("请求发生错误!: action");

    }

    public ActionException(String message) {
        super(message);
    }

    public ActionException(String message, Throwable cause) {
        super(message, cause);
    }
}
