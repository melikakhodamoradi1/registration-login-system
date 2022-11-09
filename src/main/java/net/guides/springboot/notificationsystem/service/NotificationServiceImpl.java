package net.guides.springboot.notificationsystem.service;

import net.guides.springboot.notificationsystem.model.Notification;

import net.guides.springboot.notificationsystem.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class NotificationServiceImpl implements NotificationService  {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }




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

    @Override
    public void save(Notification notification) {
        notificationRepository.save(notification);
    }
  //  @Scheduled(fixedRate =15000 )
//    private void sendNotifications() {
//        System.out.println("Sending notifications to all subscribers");
//
//        var json = """
//        {
//          "title": "Server says hello!",
//          "body": "It is now: %s"
//        }
//        """;
//
//        User.forEach(user -> {
//            send(user, String.format(json, LocalTime.now()));
//        });
//    }

}