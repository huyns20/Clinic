package com.example.clinic.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "danhmucbenh")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DanhMucBenh {
    @Id
    @Column(name = "mabenh", length = 10)
    private String maBenh;

    @Column(name = "tenbenh", nullable = false, length = 150)
    private String tenBenh;

    @Column(name = "nhombenh", length = 50)
    private String nhomBenh;

    @Column(name = "mota", columnDefinition = "TEXT")
    private String moTa;
}
