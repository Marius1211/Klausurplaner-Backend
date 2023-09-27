package de.destatis.klausurplaner;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import de.destatis.klausurplaner.entities.Exam;
import de.destatis.klausurplaner.entities.Role;
import de.destatis.klausurplaner.entities.UserEntity;
import de.destatis.klausurplaner.repositories.ExamRepository;
import de.destatis.klausurplaner.repositories.RoleRepository;
import de.destatis.klausurplaner.repositories.UserRepository;

@SpringBootApplication
public class KlausurplanerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applocationContext =
				SpringApplication.run(KlausurplanerApplication.class, args);

		Exam exam = new Exam();
		exam.setClassname("12ITa");
		exam.setSubject("Englisch");
		exam.setTopic("Cybersecurity Attacks");

		ExamRepository examRepository = applocationContext.getBean(ExamRepository.class);
		examRepository.save(exam);

		/*
		Role role = new Role();
		role.setId(1);
		role.setName("ADMIN");

		RoleRepository roleRepository = applocationContext.getBean(RoleRepository.class);
		roleRepository.save(role);

		/*
		UserEntity user = new UserEntity();
		user.setId(1);
		user.setPassword("123");
		user.setRoles();
		
		UserRepository userRepository = applocationContext.getBean(UserRepository.class);
		userRepository.save(user);
		*/
	}

	@Bean
	public static WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/exams").allowedOrigins("*");
			}
		};
	}

}
