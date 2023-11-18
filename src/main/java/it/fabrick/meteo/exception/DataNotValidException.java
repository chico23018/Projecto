package it.fabrick.meteo.exception;

import it.fabrick.meteo.classEnum.ErrorCode;
import lombok.Getter;

import javax.validation.ConstraintViolation;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class DataNotValidException extends CustomErrorException {

    private final Set<ConstraintViolation<Object>> constraintViolations;

    public DataNotValidException(String message, ErrorCode errorCode) {
        super(message, errorCode);
        this.constraintViolations = Collections.emptySet();
    }

    public DataNotValidException(ErrorCode errorCode, Set<ConstraintViolation<Object>> constraintViolations) {
        super(errorCode);
        this.constraintViolations = constraintViolations;
    }

    @Override
    public String getMessage() {
        if (constraintViolations.isEmpty()) {
            return super.getMessage();
        } else {
            return constraintViolations.stream()
                    .map(cv -> cv.getPropertyPath() + ": " + cv.getMessage())
                    .collect(Collectors.joining(", "));
        }
    }
}
