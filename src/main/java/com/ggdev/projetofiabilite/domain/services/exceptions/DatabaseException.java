package com.ggdev.projetofiabilite.domain.services.exceptions;

public class DatabaseException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public DatabaseException(Object id){
        super("Resource attached to other tables. Id "+id);
    }
}
