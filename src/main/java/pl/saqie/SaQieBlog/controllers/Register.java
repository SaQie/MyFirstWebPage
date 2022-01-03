package pl.saqie.SaQieBlog.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.saqie.SaQieBlog.service.dto.RegisterDto;
import pl.saqie.SaQieBlog.service.register.RegisterService;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
@AllArgsConstructor
public class Register {

    private final RegisterService registerService;

    @GetMapping("/register")
    public String getRegisterForm(Model model){
        model.addAttribute(new RegisterDto());
        return "/view/auth/register";
    }

    @PostMapping("/register")
    public String getUserFromRegisterForm(@ModelAttribute @Valid RegisterDto registerDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/view/auth/register";
        }
        registerService.saveUser(registerDto);
        return "redirect:/auth/login";
    }



}
