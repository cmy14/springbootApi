package com.semmyguiose.springbootapi.student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {

            Student semmy = new Student(1L, "Semmy", LocalDate.of(1989, 9, 14), "guiosesem@gmail.com");
            Student deita = new Student(2L, "Deita", LocalDate.of(2001, 6, 29), "guiosesem@gmail.com");
            Student lenny = new Student(3L, "Lenny", LocalDate.of(1993, 7, 22), "guioseslen@gmail.com");
            studentRepository.saveAll(List.of(semmy, deita, lenny));
        };

    };

}
