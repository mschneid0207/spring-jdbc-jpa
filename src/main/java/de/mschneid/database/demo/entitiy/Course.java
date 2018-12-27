package de.mschneid.database.demo.entitiy;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@NamedQueries(value = {
        @NamedQuery(name = "query_get_all_courses", query = "select c from Course c"),
        @NamedQuery(name = "query_get_all_100", query = "Select c from Course c where name like '%100%'")
})
public class Course {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "course")
    private List<Review> reviews = new ArrayList<>();

    @Setter(AccessLevel.NONE)
    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();

    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @CreationTimestamp
    private LocalDateTime createdDate;


    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public void removeReview(Review review) {
        this.reviews.remove(review);
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    @Override
    public String toString() {
        return String.format("Course(Id=%d, name=%s)", id, name);
    }




}
