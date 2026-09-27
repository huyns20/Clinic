package com.example.clinic.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.*;

@Entity
@Table(name = "dichvuyte")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DichVuYTe {
    @Id
    @Column(name = "madv", length = 10)
    private String maDV;

    @Column(name = "tendv", nullable = false, length = 150)
    private String tenDV;

    @Column(name = "dongia", nullable = false, precision = 12, scale = 0)
    @Builder.Default
    private BigDecimal donGia = BigDecimal.ZERO;
}
