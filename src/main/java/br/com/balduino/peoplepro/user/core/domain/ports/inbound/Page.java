package br.com.balduino.peoplepro.user.core.domain.ports.inbound;

import java.util.List;

public interface Page<T> {

    Integer INITIAL_PAGE_DEFAULT = 0;
    Integer PAGE_SIZE_DEFAULT = 10;

    int getTotalPages();

    long getTotalElements();

    int getSize();

    int getNumber();

    int getNumberOfElements();

    List<T> getContent();
}
