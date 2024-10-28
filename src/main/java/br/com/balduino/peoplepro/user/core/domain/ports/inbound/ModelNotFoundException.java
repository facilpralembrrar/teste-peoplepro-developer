package br.com.balduino.peoplepro.user.core.domain.ports.inbound;

public class ModelNotFoundException extends RuntimeException {

    public ModelNotFoundException(String msg) {
        super(msg);
    }
}
