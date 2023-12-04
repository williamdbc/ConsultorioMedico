package br.com.consultorio.Exception;

import jakarta.persistence.PersistenceException;

public class EntityNotFoundException extends PersistenceException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
