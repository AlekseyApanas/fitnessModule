package by.it_academy.fitness.audit;

public enum AuditEnum {
    CREATED("Создана запись в журнале продуктов"),
    UPDATE("Обновлена запись в журнале продуктов");
    private String description;

    private AuditEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
