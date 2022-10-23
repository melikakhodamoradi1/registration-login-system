package net.guides.springboot.notificationsystem.model;

import org.springframework.util.StringUtils;

import java.util.Objects;

public enum Priority {

    UNSPECIFIED(""),
    MIN("min"),
    LOW("low"),
    DEFAULT("deafult"),
    HIGH("high"),
    MAX("max");

    private String value;

    Priority(String value) {
        this.value = value;
    }

    public static Priority get(String value) {
        if (StringUtils.isEmpty(value)) {
            return UNSPECIFIED;
        }
         Priority[] arr$ = values();
        for ( Priority val : arr$) {
            if (Objects.equals(val.value, value)) {
                return val;
            }
        }
        return UNSPECIFIED;
    }

    public String getValue() {
        return value;
    }
}
