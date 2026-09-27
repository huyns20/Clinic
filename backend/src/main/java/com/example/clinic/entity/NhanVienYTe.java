package com.example.clinic.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.*;

@Entity
@Table(name = "nhanvienyte")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NhanVienYTe {
    @Id
    @Column(name = "manv", length = 10)
    private String maNV;

    @Column(name = "hoten", nullable = false, length = 100)
    private String hoTen;

    @Column(name = "gioitinh", length = 1)
    private String gioiTinh;

    @Column(name = "ngaysinh")
    private LocalDate ngaySinh;

    @Column(name = "sdt", length = 15)
    private String sdt;

    @Column(name = "makhoa", nullable = false, length = 10)
    private String maKhoa;

    @Column(name = "hesoluong", nullable = false, precision = 4, scale = 2)
    private BigDecimal heSoLuong;

    @Column(name = "ngayvaolam", nullable = false)
    private LocalDate ngayVaoLam;

    @Column(name = "loainv", nullable = false, length = 10)
    private String loaiNV; // 'BACSY' hoặc 'YTA'
}
