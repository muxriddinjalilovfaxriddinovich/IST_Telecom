package zadacha.tz.ist_telecom.component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import zadacha.tz.ist_telecom.entity.User;
import zadacha.tz.ist_telecom.entity.enums.RoleEnum;
import zadacha.tz.ist_telecom.repository.UserRepository;

@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

    final PasswordEncoder passwordEncoder;

    @Value("${spring.sql.init.mode}")
    private String mode;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;


    final UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {

        User user = new User();
        user.setUsername("company");
        user.setPassword(passwordEncoder.encode("company"));
        user.setRoleEnum(RoleEnum.COMPANY);
        userRepository.save(user);

        User admin = new User();
        admin.setRoleEnum(RoleEnum.ADMIN);
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        userRepository.save(admin);



    }
}
