package de.destatis.klausurplaner;

import java.util.ArrayList;
import java.util.List;

import de.destatis.klausurplaner.entities.Calendar;
import de.destatis.klausurplaner.entities.Klasse;
import de.destatis.klausurplaner.repositories.KlasseRepository;
import de.destatis.klausurplaner.repositories.CalendarRepository;
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

		Exam exam_test1 = new Exam();
		exam_test1.setClassname("12ITa");
		exam_test1.setStunde("1. Stunde, 30.09.2023");
		exam_test1.setSubject("Englisch");
		exam_test1.setTopic("Cybersecurity Attacks");
		exam_test1.setSontiges("Wichtig");

		ExamRepository examRepository = applocationContext.getBean(ExamRepository.class);
		examRepository.save(exam_test1);

		Exam exam_test2 = new Exam();
		exam_test2.setClassname("12ITa");
		exam_test2.setStunde("3. Stunde, 05.10.2023");
		exam_test2.setSubject("Lernfeld 12");
		exam_test2.setTopic("Cybersecurity Attacks");
		exam_test2.setSontiges("Wichtig");

		examRepository.save(exam_test2);

		Calendar cal_test1 = new Calendar();
		cal_test1.setSchulstunde("1. Stunde");
		cal_test1.setTag("Montag");
		cal_test1.setKlausurArt("Englisch Klausur");

		CalendarRepository calendarRepository = applocationContext.getBean(CalendarRepository.class);
		calendarRepository.save(cal_test1);

		Calendar cal_test2 = new Calendar();
		cal_test2.setSchulstunde("2. Stunde");
		cal_test2.setTag("Montag");
		cal_test2.setKlausurArt("Englisch Klausur");
		calendarRepository.save(cal_test2);

		Calendar cal_test3 = new Calendar();
		cal_test3.setSchulstunde("3. Stunde");
		cal_test3.setTag("Mittwoch");
		cal_test3.setKlausurArt("PoWi Klausur");
		calendarRepository.save(cal_test3);

		Calendar cal_test4 = new Calendar();
		cal_test4.setSchulstunde("4. Stunde");
		cal_test4.setTag("Mittwoch");
		cal_test4.setKlausurArt("PoWi Klausur");
		calendarRepository.save(cal_test4);

		Calendar cal_test5 = new Calendar();
		cal_test5.setSchulstunde("6. Stunde");
		cal_test5.setTag("Donnerstag");
		cal_test5.setKlausurArt("Religion Abgabe");
		calendarRepository.save(cal_test5);

		Klasse klasse_test1 = new Klasse();
		klasse_test1.setKlassenbezeichnung("12ITa");
		klasse_test1.setAnzSchueler(19);
		klasse_test1.setKlassenraum("C210");

		Klasse klasse_test2 = new Klasse();
		klasse_test2.setKlassenbezeichnung("12ITb");
		klasse_test2.setAnzSchueler(15);
		klasse_test2.setKlassenraum("C209");

		Klasse klasse_test3 = new Klasse();
		klasse_test3.setKlassenbezeichnung("12ITc");
		klasse_test3.setAnzSchueler(23);
		klasse_test3.setKlassenraum("C207");

		Klasse klasse_test4 = new Klasse();
		klasse_test4.setKlassenbezeichnung("10ITa");
		klasse_test4.setAnzSchueler(5);
		klasse_test4.setKlassenraum("C210");

		KlasseRepository klasseRepository = applocationContext.getBean(KlasseRepository.class);
		klasseRepository.save(klasse_test1);
		klasseRepository.save(klasse_test2);
		klasseRepository.save(klasse_test3);
		klasseRepository.save(klasse_test4);

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
