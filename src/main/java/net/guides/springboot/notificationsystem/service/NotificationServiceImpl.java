package net.guides.springboot.notificationsystem.service;

import net.guides.springboot.notificationsystem.entity.User;
import net.guides.springboot.notificationsystem.model.Notif;

import net.guides.springboot.notificationsystem.repository.NotificationRepository;
import net.guides.springboot.notificationsystem.repository.UserRepository;
import net.guides.springboot.notificationsystem.service.model.EmailModel;
import net.guides.springboot.notificationsystem.service.model.Grade;
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

    public void sendEmail(EmailModel emailModel , String grade) {
        /*
         * if we send userId send email for this user.
         * else send email for everyone.
         * */

        User professor = userRepository.findByEmail(Utils.getUserEmail());
//        List<User> users = userRepository.findAll(professor.getEmail());
//        User professor = userRepository.findByGrade(Grade.PROFESSOR);
        List<User> users = userRepository.findAllByGrade(Grade.valueOf(grade));
        String[] userEmails = new String[users.size()];
        MimeMessagePreparator mailMessage = mimeMessage -> {

            MimeMessageHelper message = new MimeMessageHelper(
                    mimeMessage, true);
            try {
                for (int i = 0; i < users.size(); i++) {
                    userEmails[i] = users.get(i).getEmail();
                }
                message.setFrom(sender, "مرکز آموزش های دانشگاه شهید بهشتی"); // Here comes your name

//              message.addTo(emailModel.getRecipient());


                message.setCc(userEmails);


//              message.setReplyTo(sender);
                String subject = emailModel.getSubject().concat("new message from " + professor.getName());
                message.setSubject(subject);
//              message.setSubject(emailModel.getSubject());
                message.setText(emailModel.getMsgBody());

            } catch (Exception e) {
                throw new MailSendException(emailModel.getRecipient(), e);
            }
        };

        javaMailSender.send(mailMessage);

    }



}
