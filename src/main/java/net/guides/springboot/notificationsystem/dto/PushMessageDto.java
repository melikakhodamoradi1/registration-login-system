package net.guides.springboot.notificationsystem.dto;

import com.vasl.ario.crudutil.api.validation.AddGroup;
import com.vasl.ario.crudutil.api.validation.EditGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashMap;

@Data
@ApiModel
public class PushMessageDto {
    @NotBlank(groups = { EditGroup.class})
    @ApiModelProperty(example = "1234567890")
    private String id;

    @Deprecated
    // @NotBlank(groups = {EditGroup.class, AddGroup.class})
    @ApiModelProperty(example = "AS234sfderDFS342sdfDFDFgF321LZe", position = 1)
    private Long subscriberId;

    @Deprecated
    // @NotBlank(groups = {EditGroup.class, AddGroup.class})
    @ApiModelProperty(example = "AS234sfderDFS342sdfDFDFgF321LZe", position = 1)
    private Long userId;

    // @NotBlank(groups = {EditGroup.class, AddGroup.class})
    @ApiModelProperty(example = "AS234sfderDFS342sdfDFDFgF321LZe", position = 1)
    private Long notifiableId;

    @NotBlank(groups = {EditGroup.class, AddGroup.class})
    @ApiModelProperty(example = "priority", position = 1)
    private String priority;

    @NotNull(groups = {EditGroup.class, AddGroup.class})
    @ApiModelProperty(example = "push title", position = 2)
    private String title;

    @NotNull(groups = {EditGroup.class, AddGroup.class})
    @ApiModelProperty(example = "push body", position = 2)
    private String body;

    @NotNull(groups = {EditGroup.class, AddGroup.class})
    @ApiModelProperty(example = "{ \"url\": \"http:\\\\\",\n" +
            "  \"image\": \"url\"\n }", position = 2)
    private HashMap<String, String> data;

    public Long getNotifiableId() {
        if (notifiableId != null) {
            return notifiableId;
        }
        if (userId != null) {
            return userId;
        }
        return subscriberId;
    }

}
