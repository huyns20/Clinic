package com.example.clinic.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.*;

@Entity
@Table(name = "luong")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Luong {
    @Id
    @Column(name = "maluong", length = 20)
    private String maLuong;

    @Column(name = "manv", nullable = false, length = 10)
    private String maNV;

    @Column(name = "thang", nullable = false)
    private LocalDate thang;

    @Column(name = "ngaynhanluong")
    private LocalDate ngayNhanLuong;

    @Column(name = "luongcoban", nullable = false, precision = 12, scale = 0)
    private BigDecimal luongCoBan;

    @Column(name = "tienthuong", precision = 12, scale = 0)
    @Builder.Default
    private BigDecimal tienThuong = BigDecimal.ZERO;

    @Column(name = "tongluong", nullable = false, precision = 12, scale = 0)
    private BigDecimal tongLuong;

    @Column(name = "ghichu", columnDefinition = "TEXT")
    private String ghiChu;
}
