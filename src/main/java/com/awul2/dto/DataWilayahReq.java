package com.awul2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author "Noverry Ambo"
 * @start 3/11/2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataWilayahReq {
    private Integer code;
    private String name;
    private String strcode;
}
