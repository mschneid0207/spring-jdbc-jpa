package de.mschneid.database.demo.entitiy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Review {

    @Id
    @GeneratedValue
    private Long id;
    private String rating;
    private String description;

    @ManyToOne
    private Course course;

    @Override
    public String toString() {
        return String.format("Review(Id=%d, rating=%s, description=%s)", id, rating, description);
    }
}
