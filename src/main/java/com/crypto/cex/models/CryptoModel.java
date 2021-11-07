package com.crypto.cex.models;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@Table(name = "crypto_models")
public class CryptoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lprice")
    private Double lprice;

    @Column(name = "curr1")
    private String curr1;

    @Column(name = "curr2")
    private String curr2;

    @Column(name = "times")
    private LocalDateTime times;
}
