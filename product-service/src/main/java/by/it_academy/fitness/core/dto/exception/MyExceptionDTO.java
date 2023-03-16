package by.it_academy.fitness.core.dto.exception;

public class MyExceptionDTO {
    private String field;
    private String message;

    public MyExceptionDTO() {
    }

    public MyExceptionDTO(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}