package net.guides.springboot.notificationsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("/professor")
public class ProfessorController {

    @RequestMapping(value = "/professorWorkbench")
    public String studentWork() {
//        create professorWorkbench.html
        return "professorWorkbench";
    }

}
