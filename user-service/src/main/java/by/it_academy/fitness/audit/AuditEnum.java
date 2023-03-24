package by.it_academy.fitness.audit;

public enum AuditEnum {
    CREATED("Создана запись в журнале пользователей"),
    UPDATE("Обновлена запись в журнале пользователей");
    private String description;

    private AuditEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
