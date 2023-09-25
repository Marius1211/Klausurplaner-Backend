package de.destatis.klausurplaner;

import de.destatis.klausurplaner.entities.Calendar;
import de.destatis.klausurplaner.repositories.CalendarRepository;
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
