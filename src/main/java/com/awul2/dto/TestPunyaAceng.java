package com.awul2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author "Noverry Ambo"
 * @start 3/21/2023
 */
@Data
@Builder
public class TestPunyaAceng {

    private List<Detail> data;
    private GroupTitle groupTitle;
    private HeaderData headerData;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Detail {

        private Integer roleId;
        private String roleName;
        private Integer isAktif;
        private Integer typeCompay;
        private String createdDate;
        private String disableDate;
    }

    @Data
    @Builder
    public static class GroupTitle {
        private String typePerusahaan;
    }

    @Data
    @Builder
    public static class HeaderData {
        private String key;
    }
}
