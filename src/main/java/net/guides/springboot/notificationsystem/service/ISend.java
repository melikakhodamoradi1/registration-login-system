package net.guides.springboot.notificationsystem.service;

import net.guides.springboot.notificationsystem.model.Notif;
import net.guides.springboot.notificationsystem.model.NotificationChannel;


public interface ISend {

    void send (Notif notif);

    boolean support (NotificationChannel notificationChannel);
}
