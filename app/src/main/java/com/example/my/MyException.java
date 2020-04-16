package com.example.my;

/**
 * Created by  wsl
 * on 2019/12/11 11:59
 */
public class MyException extends Exception {
    private static final long serialVersionUID = 1L;

    public MyException() {
    }

    public MyException(String message) {
        super(message);
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyException(Throwable cause) {
        super(cause);
    }


}
