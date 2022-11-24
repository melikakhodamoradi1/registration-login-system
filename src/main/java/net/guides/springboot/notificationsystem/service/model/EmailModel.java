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

   /* public static void main(String[] args) {
        System.out.println(Grade.GRADUATE.getValue()); print the persion value of the grade
        System.out.println(Grade.GRADUATE.name());     print the GRADUATE value of the grade
    }*/
}