package de.mschneid.database.demo.entitiy;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Student {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    private Passport passport;

    @Setter(AccessLevel.NONE)
    @ManyToMany
    @JoinTable(name = "STUDENT_COURSE",
    joinColumns = @JoinColumn(name = "STUDENT_ID"),
    inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))
    private List<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    @Override
    public String toString() {
        return String.format("Student(Id=%d, name=%s)", id, name);
    }
}
