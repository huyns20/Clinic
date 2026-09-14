package com.example.clinic.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
public class Doctor extends BaseEntity {
    @Column(nullable = false, unique = true, length = 30)
    private String code;

    @Column(nullable = false, length = 120)
    private String fullName;

    @Column(length = 20)
    private String phone;

    @DecimalMin("0.0")
    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal salaryCoefficient;

    // Many doctors belong to one department. No cascading deletes.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_doctor_department"))
    private Department department;
}
