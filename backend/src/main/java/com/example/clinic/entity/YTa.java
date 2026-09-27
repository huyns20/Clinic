package com.example.clinic.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "yta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class YTa {
    @Id
    @Column(name = "mayt", length = 10)
    private String maYT;

    @Column(name = "chungchihanhnghe", length = 100)
    private String chungChiHanhNghe;
}
