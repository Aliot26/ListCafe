package com.volha.listcafe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 *Created on 04.08.2021
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CafeNotFoundException extends RuntimeException {

    public CafeNotFoundException(Long id) {
        super("Could not find cafe with id = " +  id);
    }
}
