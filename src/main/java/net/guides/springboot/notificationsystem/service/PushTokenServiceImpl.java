package net.guides.springboot.notificationsystem.service;

import com.vasl.ario.crudutil.service.SimpleCRUDService;
import net.guides.springboot.notificationsystem.adapter.mapper.PushTokenServiceMapper;
import net.guides.springboot.notificationsystem.model.*;
//import net.guides.springboot.notificationsystem.repository.NotificationRepository;
import net.guides.springboot.notificationsystem.repository.PushTokenRepository;
import net.guides.springboot.notificationsystem.util.Push;

import org.springframework.security.core.token.Token;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PushTokenServiceImpl extends SimpleCRUDService<PushToken> implements PushTokenService, ISend {

    private final PushTokenRepository pushTokenRepository;
    private final Push push;

    public PushTokenServiceImpl(PushTokenRepository pushTokenRepository, Push push) {
        super(pushTokenRepository, PushTokenServiceMapper.INSTANCE);
        this.pushTokenRepository = pushTokenRepository;
        this.push = push;
    }


    @Override
    public void save(String deviceId, String token, TokenType type, Long userId) {
        if (userId == null) saveTokenForAnonymousUser(deviceId, token, type);
        else saveToken(deviceId, token, type, userId);
    }

    private void saveToken(String deviceId, String token, TokenType type, Long userId) {
         PushToken pushToken;
        var existing = pushTokenRepository.findByDeviceId(deviceId);
        if (existing.isEmpty()) {
            Random random = new Random();
            pushToken = new PushToken(random.nextLong(), userId, deviceId, token, type);
        }else {
            pushToken = existing.get();
            pushToken.setUserId(userId);
            pushToken.setToken(token);
        }
        pushTokenRepository.save(pushToken);
    }

    private void saveTokenForAnonymousUser(String deviceId, String token, TokenType type) {
        Random random = new Random();
        pushTokenRepository.save(new  PushToken(random.nextLong(),null, deviceId, token, type));
    }

    @Override
    public String sendMultiple(List<Long> userIds, String title, String body, HashMap<String, String> data, Priority priority) {
        var tokens = new ArrayList<String>();
        userIds.forEach(userId -> {
            var pushTokens = pushTokenRepository.findAllByUserId(userId);
            if (!pushTokens.isEmpty()) pushTokens.forEach(pushToken -> tokens.add(pushToken.getToken()));
        });
        push.sendPushMultiple(tokens, title, body, data, null, null);
        return null;
    }

/*
    @Override
    public void deleteByDeviceId(String deviceId) {
        pushTokenRepository.deleteByDeviceId(deviceId);
    }
*/

    @Override
    public PushToken getByPushId(Long id) {
        Optional<PushToken> byId = pushTokenRepository.findById(id);
        return byId.orElse(null);
    }

    @Override
    public void deleteByPushId(Long id) {
        pushTokenRepository.deleteById(id);
    }

///////////////////////////////////////////////////

    @Override
    public void send( Notification notification) {
        var pushTokens = pushTokenRepository.findAllByUserId(notification.getIDs().get(0));
        if (!pushTokens.isEmpty())
            pushTokens.forEach(pushToken -> push.sendPush(pushToken.getToken(), notification.getTitle(), notification.getMessage(), getMetaData(notification.getMetaData()), null, null));
    }

    private HashMap<String, String> getMetaData(Map<String, Object> metaData) {
        if (Objects.isNull(metaData)) return null;
        var newMap = metaData.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> (String) entry.getValue()));
        return (HashMap<String, String>) newMap;
    }

    @Override
    public boolean support( NotificationChannel notificationChannel) {
        return notificationChannel.equals( NotificationChannel.PUSH);
    }

//    @Override
//    public PushToken getByUserId(String userId) {
//        Optional<PushToken> optionalPushToken = PushTokenRepository.findByUserIds();
//        PushToken pushToken = null;
//        if (optionalPushToken.isPresent()){
//            pushToken = optionalPushToken.get();
//        }
//        else
//            throw new RuntimeException("push token not found for user id : " + userId);
//        return pushToken;
//    }

}