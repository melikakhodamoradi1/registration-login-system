package net.guides.springboot.notificationsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("/student")
public class UserController {

    @RequestMapping(value = "/userWorkbench")
    public String userWork() {
//        create userWorkbench.html
        return "userWorkbench";
    }

}
