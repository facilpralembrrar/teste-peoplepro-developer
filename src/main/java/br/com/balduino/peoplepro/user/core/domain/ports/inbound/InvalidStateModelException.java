package br.com.balduino.peoplepro.user.core.domain.ports.inbound;

public class InvalidStateModelException extends RuntimeException {

    public InvalidStateModelException(String msg) {
        super(msg);
    }
}
