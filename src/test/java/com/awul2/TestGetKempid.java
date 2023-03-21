package com.awul2;

import com.awul2.dto.KostKempidReq;
import com.awul2.dto.TestPunyaAceng;
import com.awul2.query.QueryDataKempid;
import com.awul2.service.AcengService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author "Noverry Ambo"
 * @start 3/21/2023
 */
@SpringBootTest
public class TestGetKempid {
    @Autowired
    AcengService acengService;

    @Autowired
    QueryDataKempid dataKempid;

    @Test
    void doGetData(){
        String data = dataKempid.getTitle(1);
        System.out.println(">>>>>>>>>>>>>>" + data);
    }
}
