package net.guides.springboot.notificationsystem.repository;//package net.guides.springboot.notificationsystem.repository;
import net.guides.springboot.notificationsystem.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.google.api.services.storage.Storage;
import net.guides.springboot.notificationsystem.entity.Role;
import net.guides.springboot.notificationsystem.model.Notification;
import net.guides.springboot.notificationsystem.model.PushToken;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, String> {

//TODO:Please change findById to findByUserIds

    Optional<Notification> findById(Long userId);
    List<Notification> findAll();
    Optional<Notification> deleteById(Long userId);
    Notification updateNotification(Long userId, Model model);


}





