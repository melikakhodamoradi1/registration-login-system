package net.guides.springboot.notificationsystem.service;

import net.guides.springboot.notificationsystem.entity.User;
import net.guides.springboot.notificationsystem.model.Notif;

import net.guides.springboot.notificationsystem.repository.NotificationRepository;
import net.guides.springboot.notificationsystem.repository.UserRepository;
import net.guides.springboot.notificationsystem.service.model.EmailModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class NotificationServiceImpl implements NotificationService  {

    private final NotificationRepository notificationRepository;

    private final JavaMailSender javaMailSender;

    private final UserRepository userRepository;

    @Value("${spring.mail.username}")
    private String sender;



    public NotificationServiceImpl(NotificationRepository notificationRepository , JavaMailSender javaMailSender, UserRepository userRepository ) {
        this.notificationRepository = notificationRepository;
        this.javaMailSender = javaMailSender;
        this.userRepository = userRepository;

    }




    @Override
    public Notif getByUserId(Long userId) {

        Optional<Notif> optionalNotification = notificationRepository.findById(userId);
        Notif notif1 = null;
        if (optionalNotification.isPresent()){
            notif1 = optionalNotification.get();
        }
        else
            throw new RuntimeException("notification not found for user id : " + userId);
        return notif1;
    }

    @Override
    public List<Notif> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public void save(Notif notif) {
        notificationRepository.save(notif);
    }





}