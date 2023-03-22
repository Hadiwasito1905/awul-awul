package com.awul2.service;

import com.awul2.dto.KostKempidReq;
import com.awul2.dto.TestPunyaAceng;
import com.awul2.dto.TypeCompanyDto;
import com.awul2.model.DataKostKempid;
import com.awul2.query.QueryDataKempid;
import com.awul2.repo.DataKostKempidRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author "Noverry Ambo"
 * @start 3/21/2023
 */

@Service
@Slf4j
public class AcengServiceImpl implements AcengService {

    @Autowired
    private DataKostKempidRepo dataKostKempidRepo;

    @Autowired
    private QueryDataKempid queryDataKempid;

    @Override
    public ResponseEntity<TestPunyaAceng> getDataAceng(Integer tipe) {

        // set header
        TestPunyaAceng.HeaderData key = TestPunyaAceng.HeaderData.builder()
                .key("kunci hitam")
                .build();

        // set detail data
        List<DataKostKempid> detailList = dataKostKempidRepo.findByTypeCompany(tipe);
        List<TestPunyaAceng.Detail> details = new ArrayList<>();
        for (DataKostKempid record : detailList){
            TestPunyaAceng.Detail model = new TestPunyaAceng.Detail();
            model.setRoleId(record.getRoleId());
            model.setRoleName(record.getRoleName());
            model.setTypeCompay(record.getTypeCompany().toString());
            model.setIsAktif(record.getIsAktif());
            model.setCreatedDate(record.getCreateDate().toString());
            model.setDisableDate(null);
            details.add(model);
        }

        // set grup title
        String groupTitle = queryDataKempid.getTitle(tipe);
        TestPunyaAceng.GroupTitle title = TestPunyaAceng.GroupTitle.builder()
                .typePerusahaan(groupTitle)
                .build();

        // set parent class
        TestPunyaAceng kempid = TestPunyaAceng.builder()
                .data(details)
                .groupTitle(title)
                .headerData(key)
                .build();

        return new ResponseEntity<>(kempid, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataKostKempid> addData(KostKempidReq req) {
        DataKostKempid dataKostKempid = new DataKostKempid();
        dataKostKempid.setRoleId(req.getRoleId());
        dataKostKempid.setRoleName(req.getRoleName());
        dataKostKempid.setTypeCompany(req.getTypeCompany());
        dataKostKempid.setCreateDate(new Date());
        dataKostKempid.setDisableDate(null);

        try{
            DataKostKempid savedData = dataKostKempidRepo.save(dataKostKempid);
            return ResponseEntity.ok(savedData);
        }catch (Exception e){
            return (ResponseEntity<DataKostKempid>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<List<TestPunyaAceng>> getFinalData() {

        List<DataKostKempid> dataKostKempids = dataKostKempidRepo.findbyGroup();
        List<TestPunyaAceng> record = new ArrayList<>();

        for (DataKostKempid value : dataKostKempids){
            TestPunyaAceng model = queryDataKempid.getFinal(value.getTypeCompany());
            record.add(model);
        }

        return new ResponseEntity<>(record, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TestPunyaAceng>> getUltimateKempid(String search) {

        List<TypeCompanyDto> headerCol = queryDataKempid.getAllKempid(search);
        List<TestPunyaAceng> record = new ArrayList<>();

        for (TypeCompanyDto value : headerCol){
            TestPunyaAceng model = queryDataKempid.finalKempidPower(value.getId());
            record.add(model);
        }

        return new ResponseEntity<>(record, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> samplePage(Integer pageNumber, Integer pageSize) {

        Pageable pageableElement = PageRequest.of(pageNumber, pageSize);
        Page<DataKostKempid> all = dataKostKempidRepo.findAll(pageableElement);

        Map<String, Object> newMap = new HashMap<>();

        newMap.put("data", all);
        return new ResponseEntity<>(newMap, HttpStatus.OK);
    }

}
