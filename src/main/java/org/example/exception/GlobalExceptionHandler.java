package org.example.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationError>> handleValidationException(MethodArgumentNotValidException exception) {
        List<ValidationError> validationErrors = exception.getBindingResult ().getFieldErrors ().stream ()
                .map (fieldError -> new ValidationError (fieldError.getField (), fieldError.getDefaultMessage ()))
                .collect (Collectors.toList ());
        validationErrors.forEach (validationError -> log.error (validationError.toString ()));
        return new ResponseEntity<> (validationErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BeeNotFoundException.class)
    public ResponseEntity<List<ValidationError>> handleBeeNotFoundException(BeeNotFoundException exception) {
        ValidationError validationErrors = new ValidationError ("beeId", "Bee not found by id: " + exception.getBeeId ());
        log.error ("Error in validation: " + validationErrors.getField () + ": " + validationErrors.getErrorMessage ());
        return new ResponseEntity<> (List.of (validationErrors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HiveNotFoundException.class)
    public ResponseEntity<List<ValidationError>> handleHiveNotFoundByIdException(HiveNotFoundException exception) {
        ValidationError validationErrors = new ValidationError ("hiveId", "Hive not found by id: " + exception.getHiveId ());
        log.error ("Error in validation: " + validationErrors.getField () + ": " + validationErrors.getErrorMessage ());
        return new ResponseEntity<> (List.of (validationErrors), HttpStatus.BAD_REQUEST);
    }
}
