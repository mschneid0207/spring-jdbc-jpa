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
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaDemoApplication.class)
public class CriteriaQueryTest {

    private Logger logger = LoggerFactory.getLogger(CriteriaQueryTest.class);

    @Autowired
    EntityManager em;

    @Test
    public void all_courses() {
        // Select c from Course c


        //1. Use Criteria Builder to create a Criteria Query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define roots for table which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        // 5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List resultList = query.getResultList();
        logger.info("Results: {} ", resultList);
    }

    @Test
    public void all_courses_having_100steps() {
        // Select c from Course c where name like '%100 Steps'


        //1. Use Criteria Builder to create a Criteria Query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define roots for table which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        // 3. Define Predicates etc using Criteria Builder
        Predicate like100Steps = cb.like(courseRoot.get("name"), "%100 steps");

        // 4. Add Predicates etc to the Criteria Query
        cq.where(like100Steps);

        // 5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List resultList = query.getResultList();
        logger.info("Results: {} ", resultList);
    }

    @Test
    public void all_courses_without_students() {
        // Select c from Course c where c.students is empty


        //1. Use Criteria Builder to create a Criteria Query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define roots for table which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        // 3. Define Predicates etc using Criteria Builder
        Predicate studentsIsEmpty = cb.isEmpty(courseRoot.get("students"));

        // 4. Add Predicates etc to the Criteria Query
        cq.where(studentsIsEmpty);

        // 5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List resultList = query.getResultList();
        logger.info("Results: {} ", resultList);
    }

    @Test
    public void all_courses_join_students() {
        // Select c from Course c join c.students s


        //1. Use Criteria Builder to create a Criteria Query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define roots for table which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        // 3. Define Predicates etc using Criteria Builder  
        Join<Object, Object> join = courseRoot.join("students");

        // 4. Add Predicates etc to the Criteria Query

        // 5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List resultList = query.getResultList();
        logger.info("Results: {} ", resultList);
    }

    @Test
    public void all_courses_left_join_students() {
        // Select c from Course c join c.students s


        //1. Use Criteria Builder to create a Criteria Query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define roots for table which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        // 3. Define Predicates etc using Criteria Builder
        Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);

        // 4. Add Predicates etc to the Criteria Query

        // 5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List resultList = query.getResultList();
        logger.info("Results: {} ", resultList);
    }

}