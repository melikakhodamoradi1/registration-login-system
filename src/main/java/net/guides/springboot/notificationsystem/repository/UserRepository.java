package net.guides.springboot.notificationsystem.repository;


import net.guides.springboot.notificationsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//@Bean
    User findByEmail(String email);

}