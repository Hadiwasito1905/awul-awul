package com.awul2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author "Noverry Ambo"
 * @start 3/16/2023
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantResp {

    private Long id;
    private String name;
    private String phoneOffice;
    private CorporateResp corporate;
    private String address;
    private ComboProvinceResp province;
    private ComboCityResp city;
    private ComboDistrictResp district;
    private String postalCode;
    private String email;
    private PicResp pic;
    private Integer status;
    private Boolean isOpen;
    private String slogan;
    private String description;
    private String whatsappNumber;
}
