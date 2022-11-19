package net.guides.springboot.notificationsystem.service;

import com.google.firebase.messaging.FirebaseMessaging;
import net.guides.springboot.notificationsystem.dto.PushNotificationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PushNotificationService {

    private Logger logger = LoggerFactory.getLogger(PushNotificationService.class);

    private FCMService fcmService;

    public PushNotificationService(FCMService fcmService) {
        this.fcmService = fcmService;
    }


    public void sendPushNotificationToToken(PushNotificationRequest request) {
        try {

            fcmService.sendMessageToToken(request);
        } catch (Exception e) {
            System.out.println("Error");
            logger.error(e.getMessage());
        }
    }

}