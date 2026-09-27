package com.example.clinic.entity;

import com.example.clinic.enums.Gender;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
public class Patient extends BaseEntity {
    @Column(nullable = false, length = 120)
    private String fullName;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Gender gender;

    // Family members may share a phone number, so this is not unique.
    @Column(nullable = false, length = 20)
    private String phone;

    @Column(length = 255)
    private String address;
}
