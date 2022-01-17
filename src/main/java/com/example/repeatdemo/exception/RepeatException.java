package com.example.repeatdemo.exception;


public class RepeatException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public RepeatException(String message){
        super(message);
    }

    public RepeatException(Throwable cause)
    {
        super(cause);
    }

    public RepeatException(String message, Throwable cause)
    {
        super(message,cause);
    }
}
