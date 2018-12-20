package de.mschneid.database.demo;

import de.mschneid.database.demo.entitiy.Course;
import de.mschneid.database.demo.entitiy.Person;
import de.mschneid.database.demo.entitiy.PersonJdbc;
import de.mschneid.database.demo.jdbc.PersonJdbcDao;
import de.mschneid.database.demo.jpa.PersonJpaRepository;
import de.mschneid.database.demo.repository.CourseRepository;
import de.mschneid.database.demo.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(JpaDemoApplication.class);

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	PersonJpaRepository jpaRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		studentRepository.saveStudentWithPassport();

		//courseRepository.playWithCreateAndUpdateTimestamp();

		//Course course = courseRepository.findById(1001L);
		//logger.info("Course with id 1000 -> {}", course);

		//courseRepository.saveCourse(Course.builder().name("Microservices in 100 Steps").build());

		//courseRepository.playWithEntityManager();




		/*logger.info("User id 1001 -> {}", jpaRepository.findById(1001));

		logger.info("All users -> {}", jpaRepository.findAll());

		logger.info("Inserting id 1003 -> {}", jpaRepository.insert(
				new Person(null, "Julian", "Hamburg", new Date())
		));
		logger.info("Updating id 1001 -> {}", jpaRepository.update(
				new Person(1001, "Laura", "Inzell", new Date())
		));
		jpaRepository.delete(1002);*/
	}
}

