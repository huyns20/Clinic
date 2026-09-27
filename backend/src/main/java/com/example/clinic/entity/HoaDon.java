package com.example.clinic.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Table(name = "hoadon")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HoaDon {
    @Id
    @Column(name = "masukien", length = 50)
    private String maSuKien;

    @Column(name = "ngaylap", nullable = false)
    @Builder.Default
    private LocalDateTime ngayLap = LocalDateTime.now();

    @Column(name = "tongtien", nullable = false, precision = 14, scale = 0)
    @Builder.Default
    private BigDecimal tongTien = BigDecimal.ZERO;

    @Column(name = "trangthaitt", length = 20)
    @Builder.Default
    private String trangThaiTT = "ChuaThanhToan"; // 'ChuaThanhToan', 'DaThanhToan'
}
