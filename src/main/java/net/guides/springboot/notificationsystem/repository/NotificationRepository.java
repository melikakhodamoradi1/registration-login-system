package net.guides.springboot.notificationsystem.repository;//package net.guides.springboot.notificationsystem.repository;
import net.guides.springboot.notificationsystem.model.Notif;
import net.guides.springboot.notificationsystem.service.model.NotifModel;
import org.springframework.stereotype.Repository;


import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends PagingAndSortingRepository<Notif, Long> {

//TODO:Please change findById to findByUserIds

    Optional<Notif> findById(Long id);
    List<Notif> findAll();
    List<NotifModel> findNotifsByUsersId(Long userId);

    List<NotifModel> findNotifsByMessageContaining(String search);


}





