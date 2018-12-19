package de.mschneid.database.demo.repository;

import de.mschneid.database.demo.entitiy.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class CourseRepository {

    @Autowired
    EntityManager em;

    public Course findById(Long id) {
        return em.find(Course.class, id);
    }

    public void deleteById(Long id) {
        Course course = findById(id);
        em.remove(course);
    }

    public Course saveCourse(Course course) {
        if (course.getId() == null) {
            em.persist(course);
        } else {
            em.merge(course);
        }
        return course;
    }

    public void playWithEntityManager() {
        Course course1 = Course.builder().name("Webservices in 100 steps").build();
        em.persist(course1);
        Course course2 = Course.builder().name("Angular in 100 steps").build();
        em.persist(course2);
        em.flush();

        // also em.clear is possible
        em.detach(course1);
        em.detach(course2);

        // entity manager updates that automatically
        course1.setName("Webservices in 100 steps - Updated");
        // entity manager updates that automatically

        em.refresh(course1);

        em.flush();


    }

}
