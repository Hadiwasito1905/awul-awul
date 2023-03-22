package com.awul2.query;

import com.awul2.dto.TestPunyaAceng;
import com.awul2.model.DataKostKempid;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author "Noverry Ambo"
 * @start 3/21/2023
 */
@Repository
@Slf4j
public class QueryDataKempid {

    private final EntityManager entityManager;

    public QueryDataKempid(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public String getTitle(Integer tipe){

        String select = "SELECT mtc.type_company FROM data_kost_kempid dkm " +
                "JOIN master_type_company mtc on dkm.type_company = mtc.type_id " +
                "WHERE dkm.type_company = :tipe " +
                "GROUP BY mtc.type_id ";

        Query query = entityManager.createNativeQuery(select);
        query.setParameter("tipe", tipe);

        String data = (String) query.getSingleResult();
        log.info("############### {} ", data);

        return data;

    }

    public Query getFinalData(Integer tipe, Boolean details){

        String condition = "";
        if (details){
            condition = "SELECT dkm.role_id, " +
                    "dkm.role_name, " +
                    "dkm.create_date, " +
                    "dkm.disable_date, " +
                    "dkm.type_company, " +
                    "dkm.is_aktif " +
                    "from data_kost_kempid dkm " +
                    "JOIN master_type_company mtc on dkm.type_company = mtc.type_id " +
                    "WHERE dkm.type_company = :tipe ";
        } else{
            condition = "SELECT mtc.type_company FROM data_kost_kempid dkm " +
                    "JOIN master_type_company mtc on dkm.type_company = mtc.type_id " +
                    "WHERE dkm.type_company = :tipe " +
                    " GROUP BY mtc.type_id ";
        }

        Query query = entityManager.createNativeQuery(condition);
        query.setParameter("tipe", tipe);

        return query;
    }

    public Integer countData(){
            String select = "SELECT count(*) FROM master_type_company ";
            Query query = entityManager.createNativeQuery(select);
            Integer count = (Integer) query.getSingleResult();

            return count;
    }

    public TestPunyaAceng getFinal(Integer tipe){
        List<TestPunyaAceng.Detail> value = new ArrayList<>();
        List<Object[]> details = getFinalData(tipe, true).getResultList();
        if (details.isEmpty()){
            TestPunyaAceng.Detail model = TestPunyaAceng.Detail.builder()
                    .roleId(null)
                    .roleName(null)
                    .isAktif(null)
                    .typeCompay(null)
                    .createdDate(null)
                    .disableDate(null)
                    .build();
            value.add(model);
        }else {
            for (Object[] record : details){
                TestPunyaAceng.Detail model = new TestPunyaAceng.Detail();
                model.setRoleId((Integer) record[0]);
                model.setRoleName((String) record[1]);
                model.setCreatedDate(record[2].toString());
                model.setDisableDate(null);
                model.setTypeCompay((Integer) record[4]);
                model.setIsAktif((Integer) record[5]);
                value.add(model);
            }
        }

        TestPunyaAceng.GroupTitle title = null;
        try {
            String groupTitle = (String) getFinalData(tipe, false).getSingleResult();
            title = TestPunyaAceng.GroupTitle.builder()
                    .typePerusahaan(groupTitle)
                    .build();
        }catch (NoResultException ex){
            ex.getMessage();
            title = TestPunyaAceng.GroupTitle.builder()
                    .typePerusahaan("KOSONG CENG CENG")
                    .build();
        }catch (NonUniqueResultException ex){
            title = TestPunyaAceng.GroupTitle.builder()
                    .typePerusahaan("Tetep GA ketemu Ceng")
                    .build();
        }

        TestPunyaAceng.HeaderData key = TestPunyaAceng.HeaderData.builder()
                .key("kunci hitam")
                .build();

        TestPunyaAceng kempid = TestPunyaAceng.builder()
                .data(value)
                .groupTitle(title)
                .headerData(key)
                .build();

        return kempid;

    }
}
