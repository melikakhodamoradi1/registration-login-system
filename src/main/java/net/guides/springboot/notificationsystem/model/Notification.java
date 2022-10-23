package net.guides.springboot.notificationsystem.model;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.util.List;
import java.util.Map;
import java.util.Set;



@Data
@Builder
public class Notification  {


    private List<String> userIds;
    private String title;
    private String message;
    //Todo
    private List<Long> IDs;
    private Set<String> visitedSet;
    private String to;
    private String mobile;
    private String template;
    private Map<String, Object> metaData;
    private List<NotificationType> types;

}
