package com.awul2.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author "Noverry Ambo"
 * @start 3/21/2023
 */
@Data
@Builder
public class KostKempidReq {
    private Integer roleId;
    private String roleName;
    private Integer typeCompany;
}
