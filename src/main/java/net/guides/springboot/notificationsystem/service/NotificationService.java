package net.guides.springboot.notificationsystem.service;


import com.vasl.ario.crudutil.service.CRUDService;
import net.guides.springboot.notificationsystem.model.Notification;
import net.guides.springboot.notificationsystem.model.PushToken;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public interface NotificationService {

    Notification getByUserId(Long userId) ;

    List<Notification> getAllNotifications() ;



}
