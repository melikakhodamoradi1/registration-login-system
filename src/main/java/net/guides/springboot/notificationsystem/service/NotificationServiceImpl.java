////package net.guides.springboot.notificationsystem.service;
////
////import net.guides.springboot.notificationsystem.model.Notification;
////import net.guides.springboot.notificationsystem.repository.NotificationRepository;
////import org.springframework.beans.factory.annotation.Autowired;
////
////
////import java.util.List;
////import java.util.Optional;
////
//public class NotificationServiceImpl implements NotificationService  {
////
////    @Autowired
////    private NotificationRepository notificationRepository;
////
////
////    @Override
////    public Notification getByUserId(String userId) {
////        Optional<Notification> optionalNotification = NotificationRepository.findByUserIds();
////        Notification notification1 = null;
////        if (optionalNotification.isPresent()){
////            notification1 = optionalNotification.get();
////        }
////        else
////            throw new RuntimeException("notification not found for user id : " + userId);
////        return notification1;
////    }
////
////    @Override
////    public void updateNotification(Notification notification) {
////        this.notificationRepository.save(notification);
////    }
////
////    @Override
////    public void deleteByUserId(Long userId) {
////        this.notificationRepository.deleteById(userId);
////    }
////
////    @Override
////    public List<Notification> getAllNotifications() {
////        return NotificationRepository.findAllByUserIds();
////    }
////
//}