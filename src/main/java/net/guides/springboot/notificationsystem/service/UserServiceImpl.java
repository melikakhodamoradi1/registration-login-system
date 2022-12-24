package net.guides.springboot.notificationsystem.service;

import net.guides.springboot.notificationsystem.dto.UserDto;
import net.guides.springboot.notificationsystem.entity.User;
import net.guides.springboot.notificationsystem.repository.RoleRepository;
import net.guides.springboot.notificationsystem.repository.UserRepository;
import net.guides.springboot.notificationsystem.service.model.Grade;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;




import net.guides.springboot.notificationsystem.entity.Role;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setGrade(Grade.valueOf(userDto.getGrade()));

        // each new user is saved in DB as a USER_ROLE
        Role role = roleRepository.findByName("ROLE_USER");
        if (role == null) {
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // TODO: 12/24/2022 Create Entity Student and set Relation Between Professor and Student
    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .filter(user -> !user.getGrade().name().equals("PROFESSOR"))
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }

    private UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        String[] str = user.getName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        userDto.setGrade(user.getGrade().getValue());
        return userDto;
    }

    private Role checkRoleExist() {
        Role role = new Role("ROLE_ADMIN", new ArrayList<>());
        return roleRepository.save(role);
    }
}
