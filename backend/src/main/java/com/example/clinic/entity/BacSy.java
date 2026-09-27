package com.example.clinic.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bacsy")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BacSy {
    @Id
    @Column(name = "mabs", length = 10)
    private String maBS;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "chuyenmon", length = 100)
    private String chuyenMon;
}
