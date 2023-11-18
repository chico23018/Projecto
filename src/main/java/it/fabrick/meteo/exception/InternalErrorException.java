package it.fabrick.meteo.exception;

import it.fabrick.meteo.classEnum.ErrorCode;

public class InternalErrorException extends CustomErrorException {

    public InternalErrorException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause, errorCode);
    }
}
