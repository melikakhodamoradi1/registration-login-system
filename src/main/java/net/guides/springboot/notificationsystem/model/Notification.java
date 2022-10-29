package net.guides.springboot.notificationsystem.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor

@Data
@Builder
@Entity
@Table(name = "notifications")
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
    private String to;
    private String mobile;
    private String template;
    @Transient
    private Map<String, Object> metaData;
    @Transient
    private List<NotificationType> types;

}
