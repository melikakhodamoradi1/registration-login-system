package net.guides.springboot.notificationsystem.controller;


import net.guides.springboot.notificationsystem.adapter.mapper.NotificationMapper;
import net.guides.springboot.notificationsystem.model.Notification;
import net.guides.springboot.notificationsystem.service.NotificationFactory;
import net.guides.springboot.notificationsystem.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
//@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationMapper mapper;

    private final NotificationService notificationService;

    private final NotificationFactory notificationFactory;

    public NotificationController(NotificationService notificationService, NotificationFactory notificationFactory) {
        this.notificationFactory = notificationFactory;
        this.mapper = NotificationMapper.INSTANCE;
        this.notificationService = notificationService;
    }


    /**
     * Send notification.
     *
     * @param notification the notification
     */
    @PostMapping("/send")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize(value = "hasAnyAuthority('notification.send')")
    public void save(@RequestBody @Valid Notification notification) {
        var channels = mapper.setChannels(notification.getTypes());
        notificationService.save( notification );
        notificationFactory.send(channels, notification);
    }
    /**
     * Gets all notifications.
     *
     *
     * @return the all notifications
     */
    @GetMapping("/get/{id}")
    public List<Notification> notificationList(){
        List <Notification> list = notificationService.getAllNotifications();
        return list;
    }

}
