package de.mschneid.database.demo.entitiy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
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
    private String name;
    @UpdateTimestamp
    private LocalDateTime updatedDate;
    @CreationTimestamp
    private LocalDateTime createdDate;

}
