package com.example.clinic.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.*;

@Entity
@Table(name = "thietbi")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ThietBi {
    @Id
    @Column(name = "mathietbi", length = 10)
    private String maThietBi;

    @Column(name = "tenthietbi", nullable = false, length = 150)
    private String tenThietBi;

    @Column(name = "dongiasudung", nullable = false, precision = 12, scale = 0)
    @Builder.Default
    private BigDecimal donGiaSuDung = BigDecimal.ZERO;
}
