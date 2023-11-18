package it.fabrick.meteo.handler;

import it.fabrick.meteo.dto.ErrorResponseDto;
import it.fabrick.meteo.exception.CustomErrorException;
import it.fabrick.meteo.exception.DataNotValidException;
import it.fabrick.meteo.exception.EntityNotFoundException;
import it.fabrick.meteo.exception.InternalErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleDataNotValidException(DataNotValidException exception) {
        return handleCustomErrorException(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleEntityNotFoundException(EntityNotFoundException exception) {
        return handleCustomErrorException(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InternalErrorException.class)
    public ResponseEntity<ErrorResponseDto> handleInternalErrorException(InternalErrorException exception) {
        return handleCustomErrorException(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponseDto> handleCustomErrorException(CustomErrorException exception, HttpStatus httpStatus) {
        log.error("<< Managed Exception >>", exception);
        return ResponseEntity.status(httpStatus).body(
                ErrorResponseDto.builder()
                        .errorCode(exception.getErrorCode())
                        .errorDescription(exception.getMessage())
                        .build());
    }
}
