package net.guides.springboot.notificationsystem.repository;//package net.guides.springboot.notificationsystem.repository;
import net.guides.springboot.notificationsystem.model.Notification;
import org.springframework.stereotype.Repository;


import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends PagingAndSortingRepository<Notification, Long> {

//TODO:Please change findById to findByUserIds

    Optional<Notification> findById(Long id);
    List<Notification> findAll();

//    Notification save(Notification notification);
//    Optional<Notification> deleteById(Long userId);
//    Notification updateNotification(Long userId, Model model);


}





