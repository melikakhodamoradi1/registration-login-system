package net.guides.springboot.notificationsystem;

import net.guides.springboot.notificationsystem.entity.Role;
import net.guides.springboot.notificationsystem.entity.User;
import net.guides.springboot.notificationsystem.model.Notif;
import net.guides.springboot.notificationsystem.repository.NotificationRepository;
import net.guides.springboot.notificationsystem.repository.UserRepository;
import net.guides.springboot.notificationsystem.service.model.Grade;
import net.guides.springboot.notificationsystem.util.CalendarTool;
import org.aspectj.weaver.ast.Not;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@SpringBootTest
class RegistrationLoginSystemApplicationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Test
    void testFindAll() {
        System.out.println(userRepository.findAll("aaa@gmail.com").size());
    }

    @Test
    void testFindUsersByGrades() {
        List<String> grades = List.of("GRADUATE", "UNDER_GRADUATE");
        List<Grade> gradesEnum = new ArrayList<>();
        for (String grade : grades) {
            gradesEnum.add(Grade.valueOf(grade));
        }
        System.out.println(userRepository.findAllByGrade(gradesEnum).size());
    }

    @Test
    void creteNotifForUser() {
        List<String> grades = List.of("GRADUATE", "UNDER_GRADUATE");
        List<Grade> gradesEnum = new ArrayList<>();
        for (String grade : grades) {
            gradesEnum.add(Grade.valueOf(grade));
        }
        Calendar calendar = Calendar.getInstance();
        Timestamp now = new Timestamp(calendar.getTimeInMillis());
        CalendarTool ca = new CalendarTool(now);
        String createAt = ca.getIranianDateTime2(new Locale("fa"));
        List<User> users = userRepository.findAllByGrade(gradesEnum);
        Set<User> userSet = new HashSet<>(users);
        Notif notif = Notif.builder()
                .title("Title")
                .createAt(createAt)
                .users(userSet)
                .build();
        notificationRepository.save(notif);
    }


    @Test
    void testLocalDateTime() {
        System.out.println(notificationRepository.findById(16L).get().getCreateAt());
    }
}