package com.alexandra.turnofacil.config;

import com.alexandra.turnofacil.models.Role;
import com.alexandra.turnofacil.models.Shift;
import com.alexandra.turnofacil.models.User;
import com.alexandra.turnofacil.repositories.ShiftRepository;
import com.alexandra.turnofacil.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
@Slf4j
public class Config {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, ShiftRepository shiftRepository) {
        return args -> {
            if (userRepository.count() == 0) {

                User admin = new User();
                admin.setUsername("alexandra_admin");
                admin.setPassword("1234");
                admin.setFullName("Alexandra Rojas");
                admin.setRole(Role.ADMIN);

                userRepository.save(admin);
                log.info("Usuario inicial creado: {}", admin.getUsername());


                Shift testShift = new Shift();
                testShift.setStartTime(LocalDateTime.now());
                testShift.setEndTime(LocalDateTime.now().plusHours(8));
                testShift.setEmployee(admin);

                shiftRepository.save(testShift);
                log.info("Turno de prueba vinculado a {} guardado con éxito.", admin.getUsername());
            }
        };
    }
}
