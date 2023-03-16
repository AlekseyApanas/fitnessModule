package by.it_academy.fitness.web.globalHandler;

import by.it_academy.fitness.core.dto.exception.MultipleErrorResponseDTO;
import by.it_academy.fitness.core.dto.exception.MyExceptionDTO;
import by.it_academy.fitness.core.dto.exception.SingleErrorResponseDTO;
import by.it_academy.fitness.core.exception.CheckDoubleException;
import by.it_academy.fitness.core.exception.CheckVersionException;
import by.it_academy.fitness.core.exception.NotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionGlobalHandler {

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public List<SingleErrorResponseDTO> onConstraintValidationException(
            ConstraintViolationException e
    ) {
        final List<SingleErrorResponseDTO> singleErrorResponseList = e.getConstraintViolations().stream().
                map(error -> new SingleErrorResponseDTO("error", e.getMessage())).collect(Collectors.toList());
        return singleErrorResponseList;
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MultipleErrorResponseDTO onMethodArgumentNotValidException(
            MethodArgumentNotValidException e
    ) {
        final List<MyExceptionDTO> myExceptions = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new MyExceptionDTO(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        return new MultipleErrorResponseDTO("structured_error", myExceptions);
    }

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public MultipleErrorResponseDTO notValidException(
            RuntimeException e
    ) {
        final List<MyExceptionDTO> myExceptions = Collections.singletonList(new MyExceptionDTO("error", e.getMessage()));
        return new MultipleErrorResponseDTO("structured_error", myExceptions);
    }

    @ExceptionHandler({CheckDoubleException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public MultipleErrorResponseDTO checkDoubleException(
            RuntimeException e
    ) {
        final List<MyExceptionDTO> myExceptions = Collections.singletonList(new MyExceptionDTO("error", e.getMessage()));
        return new MultipleErrorResponseDTO("structured_error", myExceptions);
    }

    @ExceptionHandler({CheckVersionException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MultipleErrorResponseDTO checkVersionException(
            RuntimeException e
    ) {
        final List<MyExceptionDTO> myExceptions = Collections.singletonList(new MyExceptionDTO("error", e.getMessage()));
        return new MultipleErrorResponseDTO("structured_error", myExceptions);
    }
}