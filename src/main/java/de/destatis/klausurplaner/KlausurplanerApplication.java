package de.destatis.klausurplaner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import de.destatis.klausurplaner.entities.Exam;
import de.destatis.klausurplaner.repositories.ExamRepository;

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
