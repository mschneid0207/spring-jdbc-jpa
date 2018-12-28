package de.mschneid.database.demo.repository;

import de.mschneid.database.demo.JpaDemoApplication;
import de.mschneid.database.demo.entitiy.Course;
import de.mschneid.database.demo.entitiy.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaDemoApplication.class)
public class JPQLTest {

    private Logger logger = LoggerFactory.getLogger(JPQLTest.class);

    @Autowired
    EntityManager em;

    @Test
    public void jpql_basic() {
        Query query = em.createQuery("select c from Course c");
        List resultList = query.getResultList();
        logger.info("Results: {} ", resultList);
    }

    @Test
    public void jpql_typed() {
        TypedQuery<Course> query = em.createQuery("select c from Course c", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Results jpql typed: {} ", resultList);
    }

    @Test
    public void jpql_where() {
        TypedQuery<Course> query = em.createQuery("Select c from Course c where name like '%100%'", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Results jpql where: {} ", resultList);
    }

    @Test
    public void jpql_basic_named_query() {
        Query query = em.createNamedQuery("query_get_all_courses");
        List resultList = query.getResultList();
        logger.info("Results: {} ", resultList);
    }

    @Test
    public void jpql_typed_named_query() {
        TypedQuery<Course> query = em.createNamedQuery("query_get_all_courses", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Results jpql typed: {} ", resultList);
    }

    @Test
    public void jpql_where_named_query() {
        TypedQuery<Course> query = em.createNamedQuery("query_get_all_100", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Results jpql where: {} ", resultList);
    }

    @Test
    public void jpql_courses_without_students() {
        TypedQuery<Course> query = em.createQuery("Select c from Course c where c.students is empty", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Results -> {} ", resultList);
    }

    @Test
    public void jpql_courses_with_atLeast_2_students() {
        TypedQuery<Course> query = em.createQuery("Select c from Course c where size(c.students) >= 2", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Results -> {} ", resultList);
    }

    @Test
    public void jpql_courses_with_orderedBy_students() {
        TypedQuery<Course> query = em.createQuery("Select c from Course c order by size(c.students) desc", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Results -> {} ", resultList);
    }

    @Test
    public void jpql_students_with_passport_in_a_certain_pattern() {
        TypedQuery<Student> query = em.createQuery("Select s from Student s where s.passport.number like '%123%'", Student.class);
        List<Student> resultList = query.getResultList();
        logger.info("Results -> {} ", resultList);
    }

    @Test
    public void join() {
        Query query = em.createQuery("Select c, s from Course c join c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results -> {} ", resultList.size());
        for (Object[] result: resultList) {
            logger.info("Course{} Student{}", result[0], result[1]);
        }
    }

    @Test
    public void left_join() {
        Query query = em.createQuery("Select c, s from Course c left join c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results -> {} ", resultList.size());
        for (Object[] result: resultList) {
            logger.info("Course{} Student{}", result[0], result[1]);
        }
    }

    @Test
    public void cross_join() {
        Query query = em.createQuery("Select c, s from Course c, Student s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results -> {} ", resultList.size());
        for (Object[] result: resultList) {
            logger.info("Course{} Student{}", result[0], result[1]);
        }
    }
}