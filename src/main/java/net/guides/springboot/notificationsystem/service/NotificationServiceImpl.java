package net.guides.springboot.notificationsystem.service;

import net.guides.springboot.notificationsystem.model.Notif;

import net.guides.springboot.notificationsystem.repository.NotificationRepository;
import net.guides.springboot.notificationsystem.service.model.EmailModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;


@Service
public class NotificationServiceImpl implements NotificationService  {

    private final NotificationRepository notificationRepository;

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Value("${spring.mail.name}")
    private String senderName;

    public NotificationServiceImpl(NotificationRepository notificationRepository , JavaMailSender javaMailSender) {
        this.notificationRepository = notificationRepository;
        this.javaMailSender = javaMailSender;
    }




    @Override
    public Notif getByUserId(Long userId) {
        //TODO:Please change findById to findByUserIds
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

    @Override
    public void sendEmail(EmailModel emailModel) {


        MimeMessagePreparator mailMessage = mimeMessage -> {

            MimeMessageHelper message = new MimeMessageHelper(
                    mimeMessage, true);
            try {
                message.setFrom(sender, "مرکز آموزش های دانشگاه شهید بهشتی"); // Here comes your name

                message.addTo(emailModel.getRecipient());


                message.setReplyTo(sender);
                message.setSubject(emailModel.getSubject());
                message.setText(emailModel.getMsgBody());

            } catch (Exception e) {
                throw new MailSendException(emailModel.getRecipient(), e);
            }
        };

        javaMailSender.send(mailMessage);

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