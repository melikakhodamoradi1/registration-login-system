package net.guides.springboot.notificationsystem.repository;


import net.guides.springboot.notificationsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//@Bean
    User findByEmail(String email);

    @Query(value = "SELECT u from User u where u.email !=?1")


    List<User> findAll(String email);


    List <User> findAllByGrade(String grade);



}