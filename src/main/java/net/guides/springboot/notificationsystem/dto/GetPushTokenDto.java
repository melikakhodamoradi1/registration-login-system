package net.guides.springboot.notificationsystem.dto;

import lombok.Data;
import net.guides.springboot.notificationsystem.model.TokenType;

@Data
public class GetPushTokenDto {
    private Long userId;
    private String deviceId;
    private String token;
    private TokenType type;
}
