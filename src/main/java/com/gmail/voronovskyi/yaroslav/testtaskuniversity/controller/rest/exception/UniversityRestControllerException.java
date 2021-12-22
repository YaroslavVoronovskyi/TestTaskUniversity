package com.gmail.voronovskyi.yaroslav.testtaskuniversity.controller.rest.exception;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.MappingException;
import org.springframework.dao.EmptyResultDataAccessException;

public class UniversityRestControllerException extends Exception {
    
    private static final long serialVersionUID = 1L;
    
    public UniversityRestControllerException(String errorMessage) {
        super(errorMessage);
    }
    
    public UniversityRestControllerException(String errorMessage, MappingException exception) {
        super(errorMessage, exception);
    }
    
    public UniversityRestControllerException(String errorMessage, EntityNotFoundException exception) {
        super(errorMessage, exception);
    }

    public UniversityRestControllerException(String errorMessage, EmptyResultDataAccessException exception) {
        super(errorMessage, exception);
    }
}
