package com.example.clinic.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "khoa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Khoa {
    @Id
    @Column(name = "makhoa", length = 10)
    private String maKhoa;

    @Column(name = "tenkhoa", nullable = false, length = 100)
    private String tenKhoa;

    @Column(name = "mota", columnDefinition = "TEXT")
    private String moTa;
}
