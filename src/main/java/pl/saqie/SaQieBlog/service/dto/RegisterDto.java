package pl.saqie.SaQieBlog.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterDto {

    @Size(min = 3, max = 20, message = "Nazwa musi zawierać od 3 do 20 znaków !")
    @NotBlank(message = "Nazwa nie może być pusta !")
    private String username;
    @NotBlank(message = "Hasło nie może być puste !")
    @Size(min = 3, max = 30, message = "Hasło musi zawierać od 3 do 30 znaków !")
    private String password;
    @NotBlank(message = "Email nie może być pusty !")
    @Email
    private String email;
    private String firstName;
    private String lastName;

}
