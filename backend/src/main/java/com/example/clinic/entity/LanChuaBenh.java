package com.example.clinic.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.*;

@Entity
@Table(name = "lanchuabenh")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LanChuaBenh {
    @Id
    @Column(name = "masukien", length = 50)
    private String maSuKien;

    @Column(name = "madotdieutri", nullable = false, length = 50)
    private String maDotDieuTri;

    @Column(name = "hinhthucchua", nullable = false, length = 30)
    private String hinhThucChua;

    @Column(name = "ketluan", columnDefinition = "TEXT")
    private String ketLuan;

    @Column(name = "tienchua", nullable = false, precision = 12, scale = 0)
    @Builder.Default
    private BigDecimal tienChua = BigDecimal.ZERO;

    @Column(name = "maphong", nullable = false, length = 10)
    private String maPhong;
}
