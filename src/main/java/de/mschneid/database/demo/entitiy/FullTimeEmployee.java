package de.mschneid.database.demo.entitiy;

import lombok.*;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class FullTimeEmployee extends Employee {

    public FullTimeEmployee(String name, BigDecimal salary) {
        super(name);
        this.salary = salary;
    }

    private BigDecimal salary;
}
