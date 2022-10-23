package net.guides.springboot.notificationsystem.dto;



import com.vasl.ario.crudutil.api.validation.AddGroup;
import com.vasl.ario.crudutil.api.validation.EditGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.guides.springboot.notificationsystem.model.TokenType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class PushTokenDto {

    @NotBlank(groups = {EditGroup.class})
    @ApiModelProperty(example = "1234567890")
    private String id;

    @NotBlank(groups = {EditGroup.class, AddGroup.class})
    @ApiModelProperty(example = "ANDRIOD,IOS,WEB", position = 1)
    private TokenType type;

    @NotNull(groups = {EditGroup.class, AddGroup.class})
    @NotBlank(groups = {EditGroup.class, AddGroup.class})
    @ApiModelProperty(example = "AS234sfderDFS342sdfDFDFgF321LZe", position = 2)
    private String token;

}
