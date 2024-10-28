package br.com.balduino.peoplepro.user.core.domain.ports.inbound;

public class TagAlreadyExistsForBusinessException extends InvalidStateModelException {

    public TagAlreadyExistsForBusinessException(String tagName) {
        super(String.format("Already exists an tag with name. Tag name = %s", tagName));
    }
}
