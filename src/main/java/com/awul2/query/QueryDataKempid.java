package com.awul2.query;

import com.awul2.dto.TestPunyaAceng;
import com.awul2.dto.TypeCompanyDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
                    "GROUP BY mtc.type_id ";
        }

        Query query = entityManager.createNativeQuery(condition);
        query.setParameter("tipe", tipe);

        return query;
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
                model.setTypeCompay((String) record[4]);
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

    public Query getKempidFinal(Integer tipe, Boolean details){

        String select = "";
        if (details){
            select = "select ut.id_company_type as roleId, " +
                    "        ut.name as roleName, " +
                    "        ut.created_date as createdDate, " +
                    "        ct.name as typeCompany, " +
                    "        ut.is_active as isAktif " +
                    "       from user_type ut " +
                    "JOIN company_type ct on ut.id_company_type = ct.id " +
                    "WHERE ut.id_company_type = :tipe ";
        } else {
            select = "select ct.name as typeCompany " +
                    "       from user_type ut " +
                    "JOIN company_type ct on ut.id_company_type = ct.id " +
                    "WHERE ut.id_company_type = :tipe " +
                    "GROUP BY ct.id ";
        }

        Query query = entityManager.createNativeQuery(select);
        query.setParameter("tipe", tipe);

        return query;
    }

    public List<TypeCompanyDto> getAllKempid(String search){

        String select = "select ct.id, ut.name " +
                "from company_type ct " +
                "join user_type ut on ct.id = ut.id_company_type ";

        String where = "";
        if (search == null){
            where = "";
        }else {
            where = "WHERE ut.name LIKE :search ";
        }

        String groupBy = "group by ct.name ";

        String sql = select + where + groupBy;
        Query query = entityManager.createNativeQuery(sql);
        if (search != null) {
            query.setParameter("search", "%" + search + "%");
        }

        List<Object[]> record = query.getResultList();
        List<TypeCompanyDto> newType = new ArrayList<>();
        for (Object[] value : record){
            TypeCompanyDto model = new TypeCompanyDto();
            model.setId((Integer) value[0]);
            model.setName((String) value[1]);
            newType.add(model);
        }

        return newType;

    }

    public TestPunyaAceng finalKempidPower(Integer tipe){

        List<TestPunyaAceng.Detail> value = new ArrayList<>();
        List<Object[]> details = getKempidFinal(tipe, true).getResultList();
        if (details.isEmpty()){
//            TestPunyaAceng.Detail model = TestPunyaAceng.Detail.builder()
//                    .roleId(null)
//                    .roleName(null)
//                    .isAktif(null)
//                    .typeCompay(null)
//                    .createdDate(null)
//                    .disableDate(null)
//                    .build();
//            value.add(model);
        }else {
            for (Object[] record : details){
                TestPunyaAceng.Detail model = new TestPunyaAceng.Detail();
                model.setRoleId((Integer) record[0]);
                model.setRoleName((String) record[1]);
                model.setCreatedDate(record[2].toString());
                model.setDisableDate("null");
                model.setTypeCompay((String) record[3]);
                model.setIsAktif((Integer) record[4]);
                value.add(model);
            }
        }

        TestPunyaAceng.GroupTitle title = null;
        try {
            String groupTitle = (String) getKempidFinal(tipe,false).getSingleResult();
            title = TestPunyaAceng.GroupTitle.builder()
                    .typePerusahaan(groupTitle)
                    .build();
        }catch (NoResultException ex){
            ex.getMessage();
//            title = TestPunyaAceng.GroupTitle.builder()
//                    .typePerusahaan("KOSONG CENG CENG")
//                    .build();
        }catch (NonUniqueResultException ex){
//            title = TestPunyaAceng.GroupTitle.builder()
//                    .typePerusahaan("Tetep GA ketemu Ceng")
//                    .build();
        }

        TestPunyaAceng.HeaderData key = TestPunyaAceng.HeaderData.builder()
                .key("Secret Key")
                .build();

        TestPunyaAceng kempid = TestPunyaAceng.builder()
                    .data(value)
                    .groupTitle(title)
                    .headerData(key)
                    .build();

        return kempid;
    }

}
