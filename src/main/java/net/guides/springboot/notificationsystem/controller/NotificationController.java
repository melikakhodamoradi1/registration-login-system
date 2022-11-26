package net.guides.springboot.notificationsystem.controller;


import net.guides.springboot.notificationsystem.adapter.mapper.NotificationMapper;
import net.guides.springboot.notificationsystem.dto.PushNotificationRequest;
import net.guides.springboot.notificationsystem.dto.PushNotificationResponse;
import net.guides.springboot.notificationsystem.model.Notif;
import net.guides.springboot.notificationsystem.service.NotificationFactory;
import net.guides.springboot.notificationsystem.service.NotificationService;
import net.guides.springboot.notificationsystem.service.PushNotificationService;
import net.guides.springboot.notificationsystem.service.model.EmailModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private final PushNotificationService pushNotificationService;

//    private final FireBaseMessagingService fireBaseMessagingService;

    public NotificationController(NotificationService notificationService,
                                  NotificationFactory notificationFactory,
                                  PushNotificationService pushNotificationService) {
        this.notificationService = notificationService;
        this.notificationFactory = notificationFactory;
        this.mapper = NotificationMapper.INSTANCE;
//      this.fireBaseMessagingService = fireBaseMessagingService;
        this.pushNotificationService = pushNotificationService;

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

    @PostMapping("/send-email/{grade}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sendEmail(@RequestBody EmailModel emailModel , @PathVariable String grade) {
        notificationService.sendEmail(emailModel , grade);
    }

    /*@PostMapping("/send-notification")
    @ResponseBody
    public String sendNotification(@RequestBody Notif note) throws FirebaseMessagingException, IOException, FirebaseAuthException {
        return fireBaseMessagingService.sendNotification(note, null);
    }*/



}
