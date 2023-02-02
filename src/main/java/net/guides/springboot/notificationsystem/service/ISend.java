package net.guides.springboot.notificationsystem.service;

import net.guides.springboot.notificationsystem.entity.Notif;
import net.guides.springboot.notificationsystem.model.NotificationChannel;


public interface ISend {

    void send (Notif notif);

    boolean support (NotificationChannel notificationChannel);
}
