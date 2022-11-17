package net.guides.springboot.notificationsystem.controller;


import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.internal.FirebaseService;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import net.guides.springboot.notificationsystem.adapter.mapper.NotificationMapper;
import net.guides.springboot.notificationsystem.model.Notif;
import net.guides.springboot.notificationsystem.service.FireBaseMessagingService;
import net.guides.springboot.notificationsystem.service.NotificationFactory;
import net.guides.springboot.notificationsystem.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@RestController
//@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationMapper mapper;

    private final NotificationService notificationService;

    private final NotificationFactory notificationFactory;

    private final FireBaseMessagingService fireBaseMessagingService;

    public NotificationController(NotificationService notificationService, NotificationFactory notificationFactory , FireBaseMessagingService fireBaseMessagingService) {
        this.notificationFactory = notificationFactory;
        this.mapper = NotificationMapper.INSTANCE;
        this.notificationService = notificationService;
        this.fireBaseMessagingService = fireBaseMessagingService;

    }


    /**
     * Send notification.
     *
     * @param notif the notification
     */
    @PostMapping("/send")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize(value = "hasAnyAuthority('notification.send')")
    public void save(@RequestBody @Valid Notif notif) {
        var channels = mapper.setChannels(notif.getTypes());
        notificationService.save(notif);
        notificationFactory.send(channels, notif);
    }
    /**
     * Gets all notifications.
     *
     *
     * @return the all notifications
     */
    @GetMapping("/get/{id}")
    public List<Notif> notificationList(){
        List <Notif> list = notificationService.getAllNotifications();
        return list;
    }

    @PostMapping("/send-notification")
    @ResponseBody
    public String sendNotification(@RequestBody Notif note) throws FirebaseMessagingException, IOException, FirebaseAuthException {
        return fireBaseMessagingService.sendNotification(note, null);
    }

}
