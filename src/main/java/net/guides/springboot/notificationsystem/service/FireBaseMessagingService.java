//package net.guides.springboot.notificationsystem.service;
//
//import com.google.firebase.auth.FirebaseAuthException;
//import com.google.firebase.messaging.*;
//import lombok.RequiredArgsConstructor;
//import net.guides.springboot.notificationsystem.entity.Notif;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.HashMap;
//
//@Service
//@RequiredArgsConstructor
//public class FireBaseMessagingService {
//
//
//    private final FirebaseMessaging firebaseMessaging;
//
////    @Value("${fcm.token}")
////    private String token;
//
//
//    public String sendNotification(Notif note, String token) throws FirebaseMessagingException, IOException, FirebaseAuthException {
//
//        Notification notification = Notification
//                .builder()
//                .setTitle(note.getTitle())
//                .setBody(note.getMessage())
//                .build();
//
//        Message message = Message
//                .builder()
//                .setToken("key+AAAAXCOxbgs:APA91bHbWMUj1YUglQhNjmXFqr4MMM-BSwRR1RQYOGMwOgm_1MaavH7oQIrFaU_GeCIQX-9RT8_ODZ0P9biqe51-g9oa9wW_4648oofD7wq7zErPMOT6W51tKx1jPJC4acnmdkuLQOLO")
//                .setNotification(notification)
//                .setWebpushConfig(WebpushConfig.builder()
//                        .setNotification(WebpushNotification.builder()
//                                .setSilent(false)
//                                .setRequireInteraction(true)
//                                .build())
//                        .build())
//                .putAllData(new HashMap<>())
//                .build();
//
//        return firebaseMessaging.send(message);
//    }
//
//    private static String getAccessToken() throws IOException, FirebaseAuthException {
//        return null;
//    }
//
//}
