package net.guides.springboot.notificationsystem.controller;
import net.guides.springboot.notificationsystem.dto.PushNotificationRequest;
import net.guides.springboot.notificationsystem.dto.PushNotificationResponse;
import net.guides.springboot.notificationsystem.dto.UserDto;
import net.guides.springboot.notificationsystem.entity.Role;
import net.guides.springboot.notificationsystem.entity.User;
import net.guides.springboot.notificationsystem.service.NotificationService;
import net.guides.springboot.notificationsystem.service.PushNotificationService;
import net.guides.springboot.notificationsystem.service.UserService;
import net.guides.springboot.notificationsystem.service.Utils;
import net.guides.springboot.notificationsystem.service.model.EmailModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.testng.collections.CollectionUtils;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AuthController {

    private final UserService userService;
    private final PushNotificationService pushNotificationService;
    private final NotificationService notificationService;


    public AuthController(UserService userService, PushNotificationService pushNotificationService, NotificationService notificationService) {
        this.userService = userService;
        this.pushNotificationService = pushNotificationService;
        this.notificationService = notificationService;
    }


    // handler method to handle home page request
    @GetMapping("/index")
    public String home(){
        return "index";
    }

    // handler method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    // handler method to handle list of users
    @GetMapping("/users")
    public String users(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        // user get by id

        return "users";
    }
    // handler method to handle login request
    @GetMapping("/login")
    public String login(){
        return "login";
    }




    @PostMapping("/token")
    public ResponseEntity sendTokenNotification(@RequestBody PushNotificationRequest request) {
        pushNotificationService.sendPushNotificationToToken(request);
        System.out.println("Done");
        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }

    @RequestMapping("/header")
    public String specifyUser(HttpSession session) {
        String userEmail = Utils.getUserEmail();
        User user = userService.findUserByEmail(userEmail);
        List<Role> roles = user.getRoles().stream().filter(
                role -> role.getName().endsWith("ADMIN")
        ).collect(Collectors.toList());
        if (roles.isEmpty()) {
            session.setAttribute("name",user.getName());
            return "redirect:/user/userWorkbench";
        }
        else {
            session.setAttribute("name",user.getName());
            return "redirect:/professorWorkbench";
        }

    }


}
