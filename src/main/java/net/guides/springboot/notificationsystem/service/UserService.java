package net.guides.springboot.notificationsystem.service;

import net.guides.springboot.notificationsystem.dto.UserDto;
import net.guides.springboot.notificationsystem.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}