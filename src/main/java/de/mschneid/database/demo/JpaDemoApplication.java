package de.mschneid.database.demo;

import de.mschneid.database.demo.entitiy.Person;
import de.mschneid.database.demo.entitiy.PersonJdbc;
import de.mschneid.database.demo.jdbc.PersonJdbcDao;
import de.mschneid.database.demo.jpa.PersonJpaRepository;
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
	PersonJpaRepository jpaRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("User id 1001 -> {}", jpaRepository.findById(1001));

		logger.info("All users -> {}", jpaRepository.findAll());

		logger.info("Inserting id 1003 -> {}", jpaRepository.insert(
				new Person(null, "Julian", "Hamburg", new Date())
		));
		logger.info("Updating id 1001 -> {}", jpaRepository.update(
				new Person(1001, "Laura", "Inzell", new Date())
		));
		jpaRepository.delete(1002);
	}
}

