package com.example.clinic.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Table(name = "sukienyte")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SuKienYTe {
    @Id
    @Column(name = "masukien", length = 50)
    private String maSuKien;

    @Column(name = "mabn", nullable = false, length = 10)
    private String maBN;

    @Column(name = "mabs", nullable = false, length = 10)
    private String maBS;

    @Column(name = "thoigian", nullable = false)
    @Builder.Default
    private LocalDateTime thoiGian = LocalDateTime.now();

    @Column(name = "loaisukien", nullable = false, length = 10)
    private String loaiSuKien; // 'KHAM' hoặc 'CHUA'
}
