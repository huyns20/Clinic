package com.example.clinic.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

@Entity
@Table(name = "benhnhan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BenhNhan {
    @Id
    @Column(name = "mabn", length = 10)
    private String maBN;

    @Column(name = "hoten", nullable = false, length = 100)
    private String hoTen;

    @Column(name = "gioitinh", length = 1)
    private String gioiTinh; // 'M' hoặc 'F'

    @Column(name = "ngaysinh")
    private LocalDate ngaySinh;

    @Column(name = "sdt", length = 15)
    private String sdt;

    @Column(name = "diachi", length = 200)
    private String diaChi;

    @Column(name = "socccd", length = 20, unique = true)
    private String soCCCD;

    @Column(name = "ngaydangky", nullable = false)
    @Builder.Default
    private LocalDate ngayDangKy = LocalDate.now();
}
