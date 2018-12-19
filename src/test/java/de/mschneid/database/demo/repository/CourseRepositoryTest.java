package de.mschneid.database.demo.repository;

import de.mschneid.database.demo.JpaDemoApplication;
import de.mschneid.database.demo.entitiy.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaDemoApplication.class)
public class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;

    @Test
    public void findById() {

        Course course = courseRepository.findById(1001L);
        assertEquals("JPA in 50 steps", course.getName());
    }

    @Test
    @DirtiesContext
    public void deleteById() {
        courseRepository.deleteById(1002L);
        assertNull(courseRepository.findById(1002L));
    }

    @Test
    @DirtiesContext
    public void save() {
        Course course = courseRepository.findById(1001L);
        assertEquals("JPA in 50 steps", course.getName());
        course.setName("JPA in 50 steps - Updated");
        courseRepository.saveCourse(course);

        Course course1 = courseRepository.findById(1001L);
        assertEquals("JPA in 50 steps - Updated", course.getName());
    }

    @Test
    public void playWithEntityManager() {
        courseRepository.playWithEntityManager();
    }

}