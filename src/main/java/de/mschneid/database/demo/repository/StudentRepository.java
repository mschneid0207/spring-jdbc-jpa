package de.mschneid.database.demo.repository;

import de.mschneid.database.demo.entitiy.Course;
import de.mschneid.database.demo.entitiy.Passport;
import de.mschneid.database.demo.entitiy.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;

@Repository
@Transactional
public class StudentRepository {

    @Autowired
    EntityManager em;

    public Student findById(Long id) {
        return em.find(Student.class, id);
    }

    public void deleteById(Long id) {
        Student student = findById(id);
        em.remove(student);
    }

    public Student saveStudent(Student student) {
        if (student.getId() == null) {
            em.persist(student);
        } else {
            em.merge(student);
        }
        return student;
    }

    public void saveStudentWithPassport() {
        Passport passport = Passport.builder().number("E234433").build();
        em.persist(passport);
        Student student = Student.builder().name("Laura").passport(passport).build();
        em.persist(student);
    }

    public void someDummyOperationsToUnderstandPersistenceContext() {
        // Database operation 1  - retrieve student
        Student student = em.find(Student.class, 2001L);
        // Database operation 2  - retrieve passport
        Passport passport = student.getPassport();
        // Database operation 3  - update passport
        passport.setNumber("E98876");
        // Database operation 4  - update student
        student.setName("Markus - Updated");
    }

    public void insertStudentAndCourse(Student student, Course course) {
        em.persist(student);
        em.persist(course);

        student.addCourse(course);
        course.addStudent(student);
        em.persist(student);



    }



}
