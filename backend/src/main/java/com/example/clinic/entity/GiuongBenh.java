package com.example.clinic.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.*;

@Entity
@Table(name = "giuongbenh")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GiuongBenh {
    @Id
    @Column(name = "magiuong", length = 10)
    private String maGiuong;

    @Column(name = "maphong", nullable = false, length = 10)
    private String maPhong;

    @Column(name = "trangthai", length = 20)
    @Builder.Default
    private String trangThai = "Trong"; // 'Trong' hoặc 'CoNguoi'

    @Column(name = "dongiangay", nullable = false, precision = 12, scale = 0)
    @Builder.Default
    private BigDecimal donGiaNgay = BigDecimal.ZERO;
}
