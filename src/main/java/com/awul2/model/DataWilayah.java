package com.awul2.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

/**
 * @author "Noverry Ambo"
 * @start 3/11/2023
 */
@Entity
@Table(name = "data_wilayah")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DataWilayah implements Serializable {

    private static final long serialVersionUID = -4426641774438397855L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "kode_wilayah")
    private Integer kodeWilayah;

    @Column(name = "nama_wilayah")
    private String namaWilayah;

    @Column(name = "string_kode")
    private String stringDataWil;
}
