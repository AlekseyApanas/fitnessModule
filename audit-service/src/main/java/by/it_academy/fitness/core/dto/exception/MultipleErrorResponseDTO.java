package by.it_academy.fitness.core.dto.exception;

import java.util.List;

public class MultipleErrorResponseDTO {
    String logref;
    List<MyExceptionDTO> errors;

    public MultipleErrorResponseDTO(String logref, List<MyExceptionDTO> errors) {
        this.logref = logref;
        this.errors = errors;
    }

    public MultipleErrorResponseDTO() {
    }

    public String getLogref() {
        return logref;
    }

    public List<MyExceptionDTO> getErrors() {
        return errors;
    }
}
