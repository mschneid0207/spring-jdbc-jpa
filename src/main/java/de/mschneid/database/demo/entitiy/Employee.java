package de.mschneid.database.demo.entitiy;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
//@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // needs @Entity single table with type column
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // two tables
//@Inheritance(strategy = InheritanceType.JOINED) // 3 tables with id reference
@DiscriminatorColumn(name = "EmployeeType")
public abstract class Employee {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Employee(Id=%d, name=%s)", id, name);
    }
}
