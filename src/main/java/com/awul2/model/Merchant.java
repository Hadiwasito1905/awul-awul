package com.awul2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * @author "Noverry Ambo"
 * @start 3/2/2023
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "merchant")
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "phone_office")
    private String phoneOffice;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "corporate_id")
    private Corporate corporate;
//    @Column(name = "industry_type_id")
//    private Long industryTypeId;
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
    @Column(name = "postal_code")
    private String postalCode;
    @Column(name = "longitude")
    private String longitude;
    @Column(name = "latitude")
    private String latitude;
    @Column(name = "email")
    private String email;
    @Column(name = "photo_url")
    private String photoUrl;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "pic_id")
    private Pic pic;
    @Column(name = "nib_url")
    private String nibUrl;
    @Column(name = "status")
    private Integer status;
    @Column(name = "is_open")
    private Boolean isOpen;
    @Column(name = "slogan")
    private String slogan;
    @Column(name = "description")
    private String description;
    @Column(name = "registration_form_url")
    private String registrationFormUrl;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "updated_by")
    private String updatedBy;
    @Column(name = "reference_customer_id")
    private String referenceCustomerId;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    @Column(name = "whatsapp_number")
    private String whatsappNumber;
    @Column(name = "has_custom_logistic")
    private Boolean hasCustomLogistic;
    @Column(name = "can_test_drive")
    private Boolean canTestDrive;
    @Column(name = "district_code")
    private String districtCode;
    @Column(name = "location_name")
    private String locationName;
    @Column(name = "can_credit")
    private Boolean canCredit;
    @Column(name = "official_store")
    private Boolean officialStore;
    @Column(name = "official_store_evds")
    private Boolean officialStoreEvds;
    @Column(name = "is_npwp_required")
    private Boolean isNpwpRequired;
    @Column(name = "can_shipping_discount")
    private Boolean canShippingDiscount;
    @Column(name = "can_flash_sale_discount")
    private Boolean canFlashSaleDiscount;
    @Column(name = "is_shipping_discount")
    private Boolean isShippingDiscount;
    @Column(name = "official_store_proliga")
    private Boolean officialStoreProliga;
    @Column(name = "registration_via")
    private String registrationVia;
    @Column(name = "is_subsidy_ev")
    private Boolean isSubsidyEv;
}