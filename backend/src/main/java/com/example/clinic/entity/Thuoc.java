package com.example.clinic.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.*;

@Entity
@Table(name = "thuoc")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Thuoc {
    @Id
    @Column(name = "mathuoc", length = 10)
    private String maThuoc;

    @Column(name = "tenthuoc", nullable = false, length = 150)
    private String tenThuoc;

    @Column(name = "donvitinh", length = 20)
    private String donViTinh;

    @Column(name = "dongia", nullable = false, precision = 12, scale = 0)
    private BigDecimal donGia;

    @Column(name = "hangsx", length = 100)
    private String hangSX;

    @Column(name = "tonkho")
    @Builder.Default
    private Integer tonKho = 0;
}
