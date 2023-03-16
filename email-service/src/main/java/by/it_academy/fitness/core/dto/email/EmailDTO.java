package by.it_academy.fitness.core.dto.email;

import jakarta.validation.constraints.NotBlank;

public class EmailDTO {
    @NotBlank
    private String to;
    @NotBlank
    private String subject;
    @NotBlank
    private String text;

    public EmailDTO(String subject,String to, String text) {
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }
}
