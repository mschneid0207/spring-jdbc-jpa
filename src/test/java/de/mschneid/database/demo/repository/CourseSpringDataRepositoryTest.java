package de.mschneid.database.demo.repository;

import de.mschneid.database.demo.JpaDemoApplication;
import de.mschneid.database.demo.entitiy.Course;
import de.mschneid.database.demo.entitiy.Review;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaDemoApplication.class)
public class CourseSpringDataRepositoryTest {

    @Autowired
    CourseSpringDataRepository repository;

    @Test
    public void findById_CoursePresent() {
        Optional<Course> courseOptional = repository.findById(1001L);
        assertTrue(courseOptional.isPresent());
    }

    @Test
    public void findById_CourseNotPresent() {
        Optional<Course> courseOptional = repository.findById(2001L);
        assertFalse(courseOptional.isPresent());
    }

    @Test
    public void playingAroundWithSpringDataJpaRepository() {
        Course course = Course.builder().name("New Course").build();
        repository.save(course);

        log.info("Courses -> {}", repository.findAll());
        log.info("Courses Count -> {}", repository.count());
    }

    @Test
    public void sort() {
        Course course = Course.builder().name("New Course").build();
        repository.save(course);

        Sort sort = new Sort(Sort.Direction.ASC, "name");

        log.info("Courses -> {}", repository.findAll(sort));
        log.info("Courses Count -> {}", repository.count());
    }

    @Test
    public void pagination() {
        PageRequest pageRequest = PageRequest.of(0, 2);

        Page<Course> firstPage = repository.findAll(pageRequest);
        log.info("First page -> {}", firstPage.getContent());

        Pageable secondPageable = firstPage.nextPageable();
        Page<Course> secondPage = repository.findAll(secondPageable);
        log.info("Second page -> {}", secondPage.getContent());
    }

    @Test
    public void findByName() {
        log.info("Find by name -> {}", repository.findByName("JPA in 50 steps"));
    }


}