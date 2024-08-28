package br.com.agendamento.api.exceptions.exceptions;

public class NoSuchElementException extends RuntimeException{

    public NoSuchElementException(String msg) {
        super(msg);
    }
    public NoSuchElementException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
