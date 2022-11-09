package net.guides.springboot.notificationsystem.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notifications")
@Entity
public class Notification  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private List<String> userIds;
    private String title;
    private String message;
    //Todo
    @Transient
    private List<Long> IDs;
    @Transient
    private Set<String> visitedSet;
    private String toId;
    private String mobile;
    private String template;
    @Transient
    private Map<String, Object> metaData;
    @Transient
    private List<NotificationType> types;
    private Status state;

}

