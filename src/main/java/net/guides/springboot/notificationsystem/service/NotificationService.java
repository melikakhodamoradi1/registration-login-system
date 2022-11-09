package net.guides.springboot.notificationsystem.service;


import net.guides.springboot.notificationsystem.model.Notification;
import net.guides.springboot.notificationsystem.model.NotificationChannel;
import net.guides.springboot.notificationsystem.model.TokenType;

import java.util.List;

public interface NotificationService {

    Notification getByUserId(Long userId) ;

    List<Notification> getAllNotifications() ;

//    void save(String deviceId, String token, TokenType type, Long userId);

    void save(Notification notification);
}
