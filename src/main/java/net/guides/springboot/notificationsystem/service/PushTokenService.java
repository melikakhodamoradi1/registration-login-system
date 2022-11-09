package net.guides.springboot.notificationsystem.service;

import com.vasl.ario.crudutil.service.CRUDService;
import net.guides.springboot.notificationsystem.model.Notification;
import net.guides.springboot.notificationsystem.model.Priority;
import net.guides.springboot.notificationsystem.model.PushToken;
import net.guides.springboot.notificationsystem.model.TokenType;


import java.util.HashMap;
import java.util.List;

public interface PushTokenService extends CRUDService <PushToken>, ISend {
    void save(String deviceId, String token, TokenType type, Long userId);

    String sendMultiple(List<Long> users, String title, String body, HashMap<String, String> data,  Priority priority);

   /// void deleteByDeviceId(String deviceId);

    PushToken getByPushId(Long id);

    void deleteByPushId(Long id);

//    public List<PushToken> getAllPushTokens() ;
//
//    PushToken getByUserId(String userId) ;

}
