package com.awul2.service;

import com.awul2.dto.KostKempidReq;
import com.awul2.dto.TestPunyaAceng;
import com.awul2.model.DataKostKempid;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author "Noverry Ambo"
 * @start 3/21/2023
 */

public interface AcengService {
    ResponseEntity<TestPunyaAceng> getDataAceng(Integer tipe);
    ResponseEntity<DataKostKempid> addData(KostKempidReq req);
    ResponseEntity<List<TestPunyaAceng>> getFinalData();


}
