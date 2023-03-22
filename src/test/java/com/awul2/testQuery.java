package com.awul2;

import com.awul2.dto.TestPunyaAceng;
import com.awul2.model.DataKostKempid;
import com.awul2.query.QueryDataKempid;
import com.awul2.repo.DataKostKempidRepo;
import com.awul2.service.AcengService;
import com.google.gson.Gson;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author "Noverry Ambo"
 * @start 3/22/2023
 */
@SpringBootTest
@Slf4j
public class testQuery {

    @Autowired
    AcengService acengService;
    @Autowired
    QueryDataKempid dataKempid;
    @Autowired
    DataKostKempidRepo dataKostKempidRepo;

    @Test
    void testListData(){
       Query query = dataKempid.getFinalData(4, true);
       List<Object[]> result = query.getResultList();
       List<TestPunyaAceng.Detail> value = new ArrayList<>();
        if (result.isEmpty()){
            for (Object[] record : result){
                TestPunyaAceng.Detail model = new TestPunyaAceng.Detail();
                model.setRoleId((Integer) record[0]);
                model.setRoleName((String) record[1]);
                model.setTypeCompay((Integer) record[2]);
                model.setIsAktif((Integer) record[3]);
                model.setCreatedDate((String) record[4]);
                model.setDisableDate(null);
                value.add(model);
            }
            System.out.println(value);
        }
    }

    @Test
    void testSingleData(){
        Query query = dataKempid.getFinalData(4, false);

        try {
            String result = (String) query.getSingleResult();
            System.out.println(result);
        }catch (NoResultException ex){
            System.out.println("Tidak ada hasil yang ditemukan untuk kondisi yang diberikan.");
        }catch (NonUniqueResultException ex){
            System.out.println("KOSONG CENG");
        }

    }

    @Test
    void finalAll(){
        Gson gson = new Gson();
        List<DataKostKempid> dataKostKempids = dataKostKempidRepo.findbyGroup();
        List<TestPunyaAceng> record = new ArrayList<>();

        for (DataKostKempid value : dataKostKempids){
            TestPunyaAceng model = dataKempid.getFinal(value.getTypeCompany());
            record.add(model);
        }

        System.out.println(gson.toJson(record));
    }
}
