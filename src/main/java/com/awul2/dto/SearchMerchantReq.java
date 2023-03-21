package com.awul2.dto;

import org.springframework.data.domain.Pageable;

/**
 * @author "Noverry Ambo"
 * @start 3/16/2023
 */
public record SearchMerchantReq(String fullname,
                                String storeName,
                                String phone,
                                String email,
                                Integer status,
                                Pageable pageable) {

}
