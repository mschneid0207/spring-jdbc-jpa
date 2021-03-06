package de.mschneid.database.demo;

import de.mschneid.database.demo.entitiy.PersonJdbc;
import de.mschneid.database.demo.jdbc.PersonJdbcDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

//@SpringBootApplication
public class SpringJdbcDemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(SpringJdbcDemoApplication.class);

	@Autowired
	PersonJdbcDao personJdbcDao;

	public static void main(String[] args) {
		SpringApplication.run(SpringJdbcDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("All users -> {}", personJdbcDao.findAll());
		logger.info("User id 1001 -> {}", personJdbcDao.findById(1001));
		logger.info("Deleting id 1002 -> {}", personJdbcDao.deleteById(1002));
		logger.info("Inserting id 1003 -> {}", personJdbcDao.insert(
				new PersonJdbc(1003, "Julian", "Hamburg", new Date())
		));
		logger.info("Updating id 1001 -> {}", personJdbcDao.update(
				new PersonJdbc(1001, "Laura", "Inzell", new Date())
		));
	}
}

