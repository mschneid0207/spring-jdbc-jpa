package de.mschneid.database.demo.repository;

import de.mschneid.database.demo.entitiy.Course;
import de.mschneid.database.demo.entitiy.Review;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
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

        //em.refresh(course1);

        em.flush();
    }

    public void playWithCreateAndUpdateTimestamp() {
        Course course1 = Course.builder().name("React in 100 steps").build();
        em.persist(course1);

        Course course2 = findById(1002L);
        course2.setName("JPA in 40 steps");

    }

    public void addHardcodedReviewsForCourse() {
        Course course = findById(1003L);
        log.info("course.getReviews() -> {}", course.getReviews());

        Review review1 = Review.builder().rating("5").description("Great").build();
        Review review2 = Review.builder().rating("5").description("Perfect").build();

        course.addReview(review1);
        review1.setCourse(course);
        course.addReview(review2);
        review2.setCourse(course);

        em.persist(review1);
        em.persist(review2);
    }

    public void addReviewsForCourse(Long courseId, List<Review> reviews) {
        Course course = findById(courseId);
        log.info("course.getReviews() -> {}", course.getReviews());

        reviews.forEach(review -> {
            course.addReview(review);
            review.setCourse(course);
            em.persist(review);
        });
    }

}
