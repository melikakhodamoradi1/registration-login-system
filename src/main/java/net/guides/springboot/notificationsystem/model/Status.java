package net.guides.springboot.notificationsystem.model;

public enum Status {
    UNSPECIFIED(""),
    MIN("min"),
    LOW("low"),
    DEFAULT("deafult"),
    HIGH("high"),
    MAX("max");

    private String value;

    Status(String value) {
        this.value = value;
    }
}
