package net.guides.springboot.notificationsystem.controller;

import lombok.RequiredArgsConstructor;
import net.guides.springboot.notificationsystem.dto.UserDto;
import net.guides.springboot.notificationsystem.service.UserService;
import net.guides.springboot.notificationsystem.service.model.Grade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller("/professor")
@RequiredArgsConstructor
public class ProfessorController {

    private final UserService userService;

    @RequestMapping(value = "/professorWorkbench")
    public String professorWork(Model model , HttpSession session) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("usersUndergraduate",users.stream().filter(userDto-> userDto.getGrade().equals(Grade.UNDER_GRADUATE.getValue())).collect(Collectors.toList()));
        model.addAttribute("usersGraduate",users.stream().filter(userDto-> userDto.getGrade().equals(Grade.GRADUATE.getValue())).collect(Collectors.toList()));
        model.addAttribute("userDoctoralStudent",users.stream().filter(userDto-> userDto.getGrade().equals(Grade.DOCTORAL_STUDENT.getValue())).collect(Collectors.toList()));
        model.addAttribute("usersAlumnus",users.stream().filter(userDto-> userDto.getGrade().equals(Grade.ALUMNUS.getValue())).collect(Collectors.toList()));
        model.addAttribute("usersFacultyMember",users.stream().filter(userDto-> userDto.getGrade().equals(Grade.FACULTY_MEMBER.getValue())).collect(Collectors.toList()));
        model.addAttribute("usersOther",users.stream().filter(userDto-> userDto.getGrade().equals(Grade.OTHER.getValue())).collect(Collectors.toList()));
        model.addAttribute("users",users);
        model.addAttribute("nameUser",session.getAttribute("name"));

        return "professorWorkbench";
    }



    @RequestMapping("/getUsersByGrade")
    public List<UserDto> getUsersByGrade(String grade) {
        return userService.findUsersByGrade(grade);
    }

}
