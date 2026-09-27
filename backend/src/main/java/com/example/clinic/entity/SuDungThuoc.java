package com.example.clinic.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.*;

@Entity
@Table(name = "sudungthuoc")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(SuDungThuoc.PK.class)
public class SuDungThuoc {
    @Id
    @Column(name = "masukien", length = 50)
    private String maSuKien;

    @Id
    @Column(name = "mathuoc", length = 10)
    private String maThuoc;

    @Column(name = "soluong", nullable = false)
    private Integer soLuong;

    @Column(name = "dongiaapdung", nullable = false, precision = 12, scale = 0)
    private BigDecimal donGiaApDung;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PK implements Serializable {
        private String maSuKien;
        private String maThuoc;
    }
}
