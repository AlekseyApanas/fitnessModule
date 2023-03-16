package by.it_academy.fitness.core.dto.exception;

public class SingleErrorResponseDTO {
    private String logref;
    private String massage;

    public SingleErrorResponseDTO(String logref, String massage) {
        this.logref = logref;
        this.massage = massage;
    }

    public String getLogref() {
        return logref;
    }

    public String getMassage() {
        return massage;
    }
}
