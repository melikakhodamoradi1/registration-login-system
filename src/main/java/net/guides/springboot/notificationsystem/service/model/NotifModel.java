package net.guides.springboot.notificationsystem.service.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class NotifModel {
    private String title;
    private String message;
    private String sender;
    private String createAt;

    public NotifModel(String title , String message , String sender , String createAt) {
        this.title = title;
        this.message = message;
        this.sender = sender;
        this.createAt = createAt;
    }
}
