package net.guides.springboot.notificationsystem;

import net.guides.springboot.notificationsystem.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RegistrationLoginSystemApplicationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindAll() {
        System.out.println(userRepository.findAll("aaa@gmail.com").size());
    }
}