package net.guides.springboot.notificationsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller("/student")
public class UserController {

    @RequestMapping(value = "/user/userWorkbench")
    public String userWork(Model model , HttpSession session) {
//        create userWorkbench.html
        model.addAttribute("nameUser",session.getAttribute("name"));
        return "userWorkbench";
    }

}
