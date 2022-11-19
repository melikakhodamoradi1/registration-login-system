
package net.guides.springboot.notificationsystem.service.scheduler;

import lombok.RequiredArgsConstructor;
import net.guides.springboot.notificationsystem.repository.NotificationRepository;
import net.guides.springboot.notificationsystem.repository.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendNotificationScheduler {

    private final NotificationRepository notificationRepository;

    private final UserRepository userRepository;


    @Scheduled(cron = "${spring.sendNotificationScheduler.send}")
    public void sendNotification() {
    /*    List<Notification> notifications = notificationRepository.findAll();
        for (Notification notification : notifications) {
            Long userId = notification.getToId();
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {
                String email = user.get().getEmail();
                String title = notification.getTitle();
                String message = notification.getMessage();


            }
        }*/
        System.out.println("HI");
    }
}
