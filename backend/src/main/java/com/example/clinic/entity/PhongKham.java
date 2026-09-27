package com.example.clinic.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.*;

@Entity
@Table(name = "phongkham")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhongKham {
    @Id
    @Column(name = "maphong", length = 10)
    private String maPhong;

    @Column(name = "tenphong", nullable = false, length = 100)
    private String tenPhong;

    @Column(name = "chucnang", length = 100)
    private String chucNang;

    @Column(name = "makhoa", nullable = false, length = 10)
    private String maKhoa;

    @Column(name = "dongiasudung", nullable = false, precision = 12, scale = 0)
    @Builder.Default
    private BigDecimal donGiaSuDung = BigDecimal.ZERO;
}
