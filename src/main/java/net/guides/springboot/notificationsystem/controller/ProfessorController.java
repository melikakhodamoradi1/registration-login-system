package net.guides.springboot.notificationsystem.controller;

import lombok.RequiredArgsConstructor;
import net.guides.springboot.notificationsystem.dto.UserDto;
import net.guides.springboot.notificationsystem.entity.User;
import net.guides.springboot.notificationsystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller("/professor")
@RequiredArgsConstructor
public class ProfessorController {

    private final UserService userService;

    @RequestMapping(value = "/professorWorkbench")
    public String professorWork(Model model , HttpSession session) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users",users);
        model.addAttribute("nameUser",session.getAttribute("name"));
//        create professorWorkbench.html

        return "professorWorkbench";
    }

}
