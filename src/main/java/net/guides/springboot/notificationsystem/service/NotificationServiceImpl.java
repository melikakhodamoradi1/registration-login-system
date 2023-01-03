package net.guides.springboot.notificationsystem.service;

import net.guides.springboot.notificationsystem.entity.User;
import net.guides.springboot.notificationsystem.model.Notif;

import net.guides.springboot.notificationsystem.repository.NotificationRepository;
import net.guides.springboot.notificationsystem.repository.UserRepository;
import net.guides.springboot.notificationsystem.service.model.EmailModel;
import net.guides.springboot.notificationsystem.service.model.Grade;
import net.guides.springboot.notificationsystem.service.model.NotifModel;
import net.guides.springboot.notificationsystem.util.CalendarTool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    private final JavaMailSender javaMailSender;

    private final UserRepository userRepository;

    @Value("${spring.mail.username}")
    private String sender;


    public NotificationServiceImpl(NotificationRepository notificationRepository, JavaMailSender javaMailSender, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.javaMailSender = javaMailSender;
        this.userRepository = userRepository;

    }


    @Override
    public Notif getByUserId(Long userId) {

        Optional<Notif> optionalNotification = notificationRepository.findById(userId);
        Notif notif1 = null;
        if (optionalNotification.isPresent()) {
            notif1 = optionalNotification.get();
        } else
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

    public void sendEmail(MultipartFile file,EmailModel emailModel, List<String> grades) {
        /*
         * if we send userId send email for this user.
         * else send email for everyone.
         * */

        User professor = getUserByEmail(Utils.getUserEmail());
        String createAt = getCreateAtTimeString();


//        List<User> users = userRepository.findAll(professor.getEmail());
//        User professor = userRepository.findByGrade(Grade.PROFESSOR);
        List<Grade> gradesEnum = new ArrayList<>();
        for (String grade : grades) {
            gradesEnum.add(Grade.valueOf(grade));
        }

        List<User> users = userRepository.findAllByGrade(gradesEnum);
        Set<User> userSet = new HashSet<>(users);
        userSet.add(professor);
        Notif notif = Notif.builder()
                .message(emailModel.getMsgBody())
                .title(emailModel.getSubject())
                .createAt(createAt)
                .sender(professor.getName())
                .users(userSet)
                .build();
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
                String subject = emailModel.getSubject().concat("new message from " + professor.getName());
                message.setSubject(subject);
                message.setText(emailModel.getMsgBody());
                if (!file.isEmpty())
                    message.addAttachment(StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())),file);


            } catch (Exception e) {
                throw new MailSendException(emailModel.getRecipient(), e);
            }
        };

        javaMailSender.send(mailMessage);
        notificationRepository.save(notif);
        System.out.println("Send Email Successfully");

    }

    @Override
    public List<NotifModel> getListsNotifModel() {
        User user = getUserByEmail(Utils.getUserEmail());
        return notificationRepository.findNotifsByUsersId(user.getId()).stream()
                .peek(notifModel -> notifModel.setCreateAt(fixedCreatAtToPersian(notifModel.getCreateAt())))
                .collect(Collectors.toList());
    }

    @Override
    public List<NotifModel> getListsNotifModelWithHashtag(String searchKey) {
        return notificationRepository.findNotifsByMessageContaining(searchKey)
                .stream()
                .peek(notifModel -> notifModel.setCreateAt(fixedCreatAtToPersian(notifModel.getCreateAt())))
                .collect(Collectors.toList());
    }

    private String getCreateAtTimeString() {
        Calendar calendar = Calendar.getInstance();
        Timestamp now = new Timestamp(calendar.getTimeInMillis());
        CalendarTool ca = new CalendarTool(now);
        return ca.getIranianDateTime2(new Locale("fa"));
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private String fixedCreatAtToPersian(String createAt) {
        String[] dateAndTime = createAt.split(" ");
        return dateAndTime[1] + " " + dateAndTime[0];
    }


}
