package net.guides.springboot.notificationsystem.service.model;

public enum Grade {

    UNDER_GRADUATE("دانشجوی کارشناسی") , GRADUATE("دانشجوی کارشناسی ارشد") , PROFESSOR ( "اُستاد") ,
    DOCTORAL_STUDENT ("دانشجوی دکتری");

    private final String value;
    Grade(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
