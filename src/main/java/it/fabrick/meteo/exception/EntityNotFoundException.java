package it.fabrick.meteo.exception;

import it.fabrick.meteo.classEnum.ErrorCode;

public class EntityNotFoundException extends CustomErrorException {

    public EntityNotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
