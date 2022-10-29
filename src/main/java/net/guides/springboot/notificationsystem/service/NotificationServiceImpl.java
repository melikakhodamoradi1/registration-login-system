package net.guides.springboot.notificationsystem.service;

import com.vasl.ario.crudutil.service.SimpleCRUDService;
import net.guides.springboot.notificationsystem.model.Notification;

import net.guides.springboot.notificationsystem.model.PushToken;
import net.guides.springboot.notificationsystem.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;


import java.util.List;
import java.util.Optional;

public class NotificationServiceImpl implements NotificationService  {

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Autowired
    private NotificationRepository notificationRepository;


    @Override
    public Notification getByUserId(Long userId) {
        //TODO:Please change findById to findByUserIds
        Optional<Notification> optionalNotification = notificationRepository.findById(userId);
        Notification notification1 = null;
        if (optionalNotification.isPresent()){
            notification1 = optionalNotification.get();
        }
        else
            throw new RuntimeException("notification not found for user id : " + userId);
        return notification1;
    }

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

}