package pl.saqie.SaQieBlog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Rules {

    @GetMapping("/rules")
    public String getRulesPage(){
        return "/view/home/rules";
    }

}
