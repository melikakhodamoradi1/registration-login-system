package net.guides.springboot.notificationsystem.util;

import com.google.firebase.messaging.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Component
public class Push {

    @Async
    public void sendPush(String token, String title, String body, HashMap<String, String> data, String icon, String color) {
        try {
            List<String> tokens = new ArrayList<String>();
            if (!StringUtils.isEmpty(token))
                tokens.add(token);
            if (tokens.size() > 0)
                sendPushMultiple(tokens, title, body, data, icon, color);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Async
    public void sendPushMultiple(List<String> tokens, String title, String body, HashMap<String, String> data, String icon, String color) {
        try {
            // See documentation on defining a message payload.
            if (StringUtils.isEmpty(icon))
                icon = "stock_ticker_update";
            if (StringUtils.isEmpty(color))
                color = "#f45342";

            Message.Builder builder = Message.builder();
            for (int i = 0; i < tokens.size(); i++) {
                builder = builder.setToken(tokens.get(i));
            }
            Message message = builder.setNotification(Notification.builder()
                            .setTitle(title)
                            .setBody(body)
                            .build())
                    .setApnsConfig(ApnsConfig.builder()
                            .setAps(Aps.builder()
                                    .setSound("default")
                                    .build())
                            .build())
                    .setWebpushConfig(WebpushConfig.builder()
                            .setNotification(WebpushNotification.builder()
                                    .setSilent(false)
                                    .setRequireInteraction(true)
                                    .build())
                            .build())
                    .putAllData(Objects.nonNull(data)?data:new HashMap<>())
                    .build();

            // Send a message to the device corresponding to the provided
            // registration token.
            String response = null;
            try {
                response = FirebaseMessaging.getInstance().send(message);
            } catch (FirebaseMessagingException e) {
                e.printStackTrace();
            }
            // Response is a message ID string.
            System.out.println("Successfully sent message: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Async
    public void sendPushMultiple(List<String> tokens, String title, String body, HashMap<String, String> data) {
        try {
            // See documentation on defining a message payload.
            Message.Builder builder = Message.builder();
            for (String token : tokens) {
                builder = builder.setToken(token);
            }
            Message message = builder.setNotification(Notification.builder()
                            .setTitle(title)
                            .setBody(body)
                            .build())
                    .setAndroidConfig(AndroidConfig.builder()
                            .setTtl(3600 * 1000)
                            .setNotification(AndroidNotification.builder()
                                    .setIcon("stock_ticker_update")
                                    .setColor("#f45342")
                                    .build())
                            .build())
                    .putAllData(data)
                    .build();

            // Send a message to the device corresponding to the provided
            // registration token.
            String response = null;
            try {
                response = FirebaseMessaging.getInstance().send(message);
            } catch (FirebaseMessagingException e) {
                e.printStackTrace();
            }
            // Response is a message ID string.
            System.out.println("Successfully sent message: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
