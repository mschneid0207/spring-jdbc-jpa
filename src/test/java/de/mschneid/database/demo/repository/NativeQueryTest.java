package de.mschneid.database.demo.repository;

import de.mschneid.database.demo.JpaDemoApplication;
import de.mschneid.database.demo.entitiy.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaDemoApplication.class)
public class NativeQueryTest {

    private Logger logger = LoggerFactory.getLogger(NativeQueryTest.class);

    @Autowired
    EntityManager em;

    @Test
    public void native_queries_basic() {
        Query query = em.createNativeQuery("SELECT * From COURSE", Course.class);
        List resultList = query.getResultList();
        logger.info("Results: {} ", resultList);
    }

    @Test
    public void native_queries_basic_with_parameters() {
        Query query = em.createNativeQuery("SELECT * From COURSE where id = ?", Course.class);
        query.setParameter(1, 1001L);
        List resultList = query.getResultList();
        logger.info("Results: {} ", resultList);
    }

    @Test
    public void native_queries_basic_with_named_parameters() {
        Query query = em.createNativeQuery("SELECT * From COURSE where id = :id", Course.class);
        query.setParameter("id", 1001L);
        List resultList = query.getResultList();
        logger.info("Results: {} ", resultList);
    }

    @Test
    @Transactional
    public void native_queries_to_update() {
        Query query = em.createNativeQuery("update COURSE set updated_date=sysdate()");
        int noOfRowsUpdated = query.executeUpdate();
        logger.info("Updated rows: {} ", noOfRowsUpdated);
    }


}