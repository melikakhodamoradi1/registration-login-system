package net.guides.springboot.notificationsystem.service;

import net.guides.springboot.notificationsystem.model.Notification;
import net.guides.springboot.notificationsystem.model.NotificationChannel;


public interface ISend {

    void send (Notification notification);

    boolean support (NotificationChannel notificationChannel);
}
