package com.example.clinic.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

@Entity
@Table(name = "dotdieutri")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DotDieuTri {
    @Id
    @Column(name = "madotdieutri", length = 50)
    private String maDotDieuTri;

    @Column(name = "masukienkham", nullable = false, length = 50)
    private String maSuKienKham;

    @Column(name = "mabenh", nullable = false, length = 10)
    private String maBenh;

    @Column(name = "mucdonang", length = 10)
    private String mucDoNang; // 'Nhe', 'Vua', 'Nang'

    @Column(name = "solanchuadukien")
    private Integer soLanChuaDuKien;

    @Column(name = "ngaybatdau", nullable = false)
    private LocalDate ngayBatDau;

    @Column(name = "ngayketthuc")
    private LocalDate ngayKetThuc;

    @Column(name = "trangthai", nullable = false, length = 20)
    @Builder.Default
    private String trangThai = "DangDieuTri"; // 'DangDieuTri', 'DaKhoi'

    @Column(name = "magiuong", length = 10)
    private String maGiuong;

    @Column(name = "madottruoc", length = 50)
    private String maDotTruoc;
}
