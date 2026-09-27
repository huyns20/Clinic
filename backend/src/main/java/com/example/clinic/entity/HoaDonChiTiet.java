package com.example.clinic.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.*;

@Entity
@Table(name = "hoadonchitiet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(HoaDonChiTiet.PK.class)
public class HoaDonChiTiet {
    @Id
    @Column(name = "masukien", length = 50)
    private String maSuKien;

    @Id
    @Column(name = "sodong", nullable = false)
    private Integer soDong;

    @Column(name = "motakhoanmuc", length = 200)
    private String moTaKhoanMuc;

    @Column(name = "sotien", nullable = false, precision = 12, scale = 0)
    private BigDecimal soTien;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PK implements Serializable {
        private String maSuKien;
        private Integer soDong;
    }
}
