package net.guides.springboot.notificationsystem.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import net.guides.springboot.notificationsystem.model.TokenType;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel
public class CreatePushTokenDto {
    @NotBlank
    private String deviceId;
    @NotBlank
    private String token;
    private TokenType type = TokenType.UNSPECIFIED;
}
