package de.mschneid.database.demo.repository;

import de.mschneid.database.demo.entitiy.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {

    List<Course> findByName(String name);
    List<Course> findByNameAndId(String name, Long id);
    List<Course> findByNameOrOrderById(String name);
    List<Course> deleteByName(String name);
    List<Course> countByName(String name);

    @Query("Select c from Course c where name like '%100%'")
    List<Course> courseWith100StepsInName();

    @Query(value = "Select * from Course c where name like '%100%'", nativeQuery = true)
    List<Course> courseWith100StepsInNameUsingNativeQuery();

    @Query(name = "query_get_all_100")
    List<Course> courseWith100StepsInNameUsingNamedQuery();



}
