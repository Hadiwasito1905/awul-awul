package com.awul2;

import com.awul2.dto.KostKempidReq;
import com.awul2.service.AcengService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author "Noverry Ambo"
 * @start 3/21/2023
 */
@SpringBootTest
public class TestAddKempid {
    @Autowired
    AcengService acengService;

    @Test
    void doAddData(){
        KostKempidReq kostKempidReq = KostKempidReq.builder()
                .roleId(1)
                .roleName("user")
                .typeCompany(3)
                .build();

        acengService.addData(kostKempidReq);
    }
}
