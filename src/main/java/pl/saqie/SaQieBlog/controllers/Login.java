package pl.saqie.SaQieBlog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class Login {

    @GetMapping("/login")
    public String getLoginForm(){
        return "/view/auth/login";
    }

}
