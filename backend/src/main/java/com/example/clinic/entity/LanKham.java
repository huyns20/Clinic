package com.example.clinic.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.*;

@Entity
@Table(name = "lankham")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LanKham {
    @Id
    @Column(name = "masukien", length = 50)
    private String maSuKien;

    @Column(name = "makhoa", nullable = false, length = 10)
    private String maKhoa;

    @Column(name = "trieuchung", columnDefinition = "TEXT")
    private String trieuChung;

    @Column(name = "tienkham", nullable = false, precision = 12, scale = 0)
    @Builder.Default
    private BigDecimal tienKham = BigDecimal.ZERO;
}
