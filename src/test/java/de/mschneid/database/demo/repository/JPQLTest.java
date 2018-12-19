package de.mschneid.database.demo.repository;

import de.mschneid.database.demo.JpaDemoApplication;
import de.mschneid.database.demo.entitiy.Course;
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
}