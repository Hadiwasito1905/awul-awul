package com.awul2.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

/**
 * @author "Noverry Ambo"
 * @start 3/21/2023
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "data_kost_kempid")
public class DataKostKempid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Integer roleId;
    private String roleName;
    private Integer typeCompany;
    private Integer isAktif;
    private Date createDate;
    private Date disableDate;
}
