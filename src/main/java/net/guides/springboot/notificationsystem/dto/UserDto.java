package net.guides.springboot.notificationsystem.dto;



import lombok.*;
import net.guides.springboot.notificationsystem.service.model.Grade;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    private Long id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty(message = "ایمیل نباید خالی باشد")
    @Email
    private String email;
    @NotEmpty(message = "رمز عبور نباید خالی باشد")
    private String password;
    private String grade;
    /**/
}