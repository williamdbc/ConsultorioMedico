package br.com.consultorio.Exception;

import jakarta.persistence.PersistenceException;

public class EntityNotFoundExcepction extends PersistenceException {
    public EntityNotFoundExcepction(String message) {
        super(message);
    }
}
