package net.guides.springboot.notificationsystem.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailModel {
    private String recipient; // send to
    private String msgBody;
    private String subject;
    private String attachment;
}
