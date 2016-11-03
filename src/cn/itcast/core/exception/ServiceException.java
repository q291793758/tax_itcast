package cn.itcast.core.exception;

public class ServiceException extends SysException {

    public ServiceException() {
        super("业务操作发生错误！ :service");

    }

    public ServiceException(String message) {
        super(message);
    }
}
