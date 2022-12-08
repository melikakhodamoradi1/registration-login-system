package net.guides.springboot.notificationsystem.repository;


import net.guides.springboot.notificationsystem.entity.User;
import net.guides.springboot.notificationsystem.service.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//@Bean
    User findByEmail(String email);

    @Query(value = "SELECT u from User u where u.email !=?1")
    List<User> findAll(String email);


    @Query(value = "SELECT u from User u where u.grade in (:grades)")
    List <User> findAllByGrade(@Param("grades") List<Grade> grade);

    User findByGrade(Grade grade);



}