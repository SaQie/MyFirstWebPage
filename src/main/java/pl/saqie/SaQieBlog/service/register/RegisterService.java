package pl.saqie.SaQieBlog.service.register;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.saqie.SaQieBlog.domain.User;
import pl.saqie.SaQieBlog.service.dto.RegisterDto;
import pl.saqie.SaQieBlog.repository.UserRepository;

@Service
@AllArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public void saveUser(RegisterDto registerDto){
        User user = new User(
                registerDto.getUsername(),
                registerDto.getPassword(),
                registerDto.getEmail(),
                registerDto.getFirstName(),
                registerDto.getLastName(),
                "USER"
        );
        encodePasswordAndSaveToDatabase(user);
    }

    private void encodePasswordAndSaveToDatabase(User user){
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

}
