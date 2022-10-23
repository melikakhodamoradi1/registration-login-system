package net.guides.springboot.notificationsystem.controller;


import lombok.RequiredArgsConstructor;


import net.guides.springboot.notificationsystem.adapter.mapper.NotificationMapper;
import net.guides.springboot.notificationsystem.model.Notification;
import net.guides.springboot.notificationsystem.service.NotificationFactory;
//import net.guides.springboot.notificationsystem.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationFactory notificationFactory;

    private final NotificationMapper mapper;


   // private final NotificationService notificationService;



    /**
     * Send notification.
     *
     * @param notification the notification
     */
    @PostMapping("/send")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize(value = "hasAnyAuthority('notification.send')")
    public void send(Notification notification) {
        var channels = mapper.setChannels(notification.getTypes());
        notificationFactory.send(channels, notification);
    }
//    /**
//     * Gets all notifications.
//     *
//     *
//     * @return the all notifications
//     */
//    @GetMapping("/get/{id}")
//    public List<Notification>notificationList(){
//        List <Notification> list = notificationService.getAllNotifications();
//        return list;
//    }
//
//
//    /**
//     * Delete.
//     *
//     * @param id the object id
//     */
//
//    @GetMapping("/delete/{id}")
//    public String deleteNotificationByUserId(@PathVariable (value = "id") Long id) {
//
//        this.notificationService.deleteByUserId(id);
//        return "redirect:/";
//    }
//
//
//    /**
//     * Update.
//     *
//     *
//     * @param id  the id
//     */
//
//    @GetMapping("/update/{id}")
//    public String updateNotification(@PathVariable( value = "id") Long id, Model model) {
//
//        Notification notification = notificationService.updateNotification( id, model );
//        model.addAttribute("notification", notification);
//        return "update_notification";
//    }

}
