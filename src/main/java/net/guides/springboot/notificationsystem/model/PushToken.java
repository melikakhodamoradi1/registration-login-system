package net.guides.springboot.notificationsystem.model;

import lombok.*;


import javax.persistence.*;

@Data
//@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "push.token")
@Entity
public class PushToken   {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Id
//    //Todo

    private Long userId;
    private String deviceId;
    private String token;
    private TokenType type;




}
