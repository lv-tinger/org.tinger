package org.tinger.mysql.exception;

public class JdbcHandlerException extends RuntimeException {
    public JdbcHandlerException(int columnIndex, Throwable throwable) {
        super(throwable);
    }
}
