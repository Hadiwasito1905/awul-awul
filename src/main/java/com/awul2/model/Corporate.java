package com.awul2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * @author Ibrahim Manorek
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "corporate")
public class Corporate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 255)
    private String name;
    @Column(name = "area_name", length = 255)
    private String areaName;
    @Column(name = "phone_office", length = 255)
    private String phoneOffice;
    @Column(name = "address")
    private String address;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "province_id")
    private Province province;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "city_id")
    private City city;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "district_id")
    private District district;
    @Column(name = "email")
    private String email;
    @Column(name = "fax_number")
    private String faxNumber;
    @Column(name = "npwp")
    private String npwp;
    @Column(name = "npwp_url")
    private String npwpUrl;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "pic_id")
    private Pic pic;
    @Column(name = "status")
    private Integer status;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "updated_by")
    private String updatedBy;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
