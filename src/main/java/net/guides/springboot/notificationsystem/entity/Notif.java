package net.guides.springboot.notificationsystem.entity;
import lombok.*;
import net.guides.springboot.notificationsystem.entity.User;
import net.guides.springboot.notificationsystem.model.NotificationType;
import net.guides.springboot.notificationsystem.model.Status;

import javax.persistence.*;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notifications")
@Entity
@Builder
@ToString
public class Notif {
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
    private Long toId;
    private String mobile;
    private String template;
    @Transient
    private Map<String, Object> metaData;
    @Transient
    private List<NotificationType> types;
    private Status state;
    private String senderId;
    private String sender;

    private String createAt;

    @ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.MERGE)
    @JoinTable(
            name = "users_notif",
            joinColumns = {@JoinColumn(name = "notif_id" , referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")}
    )
    private Set<User> users = new HashSet<>();

    public Notif(String title , String message ,String sender , String createAt) {
        this.title = title;
        this.message = message;
        this.sender = sender;
        this.createAt = createAt;
    }

}

