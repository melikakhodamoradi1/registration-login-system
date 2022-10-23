package net.guides.springboot.notificationsystem.service;

import lombok.RequiredArgsConstructor;
import net.guides.springboot.notificationsystem.model.Notification;
import net.guides.springboot.notificationsystem.model.NotificationChannel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class NotificationFactory  {
    private final List<ISend> sendersList;

    public void send(List<NotificationChannel> notificationChannels , Notification notification) {
        var senders = new ArrayList<ISend>();
        notificationChannels.forEach(notificationChannel ->
                senders.add(sendersList
                        .stream()
                        .filter(notificationService -> notificationService.support(notificationChannel))
                        .findFirst()
                        .orElseThrow())
        );
        senders.forEach(sender -> sender.send(notification));
    }

}
