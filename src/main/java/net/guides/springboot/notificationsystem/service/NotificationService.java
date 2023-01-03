package net.guides.springboot.notificationsystem.service;


import net.guides.springboot.notificationsystem.model.Notif;
import net.guides.springboot.notificationsystem.service.model.EmailModel;
import net.guides.springboot.notificationsystem.service.model.NotifModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NotificationService {

    Notif getByUserId(Long userId) ;

    List<Notif> getAllNotifications() ;

//  void save(String deviceId, String token, TokenType type, Long userId);

    void save(Notif notif);

    void sendEmail(MultipartFile file,EmailModel emailModel , List<String> grades);

    List<NotifModel> getListsNotifModel();

    List<NotifModel> getListsNotifModelWithHashtag(String searchKey);


}
