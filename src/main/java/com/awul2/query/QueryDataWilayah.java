package com.awul2.query;

import com.awul2.dto.TestPunyaAceng;
import com.awul2.model.DataWilayah;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author "Noverry Ambo"
 * @start 3/11/2023
 */
@Repository
@Slf4j
public class QueryDataWilayah {

    private final EntityManager entityManager;

    public QueryDataWilayah(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<DataWilayah> findByUid(String uid){

        List<Integer> listArray = strToInt(uid);

        String select = "SELECT * FROM data_wilayah  WHERE kode_wilayah IN(:uid) ";

        Query query = entityManager.createNativeQuery(select, DataWilayah.class);
        query.setParameter("uid", listArray);

        List<DataWilayah> list = query.getResultList();

        return list;
    }

    public String data(String uid){
        String select = "SELECT JSON_OBJECT('data', JSON_ARRAY(JSON_OBJECT('id',id,\n" +
                "    'namaWIlayah', nama_wilayah))) from data_wilayah where kode_wilayah = :uid ";

        Query query = entityManager.createNativeQuery(select);
        query.setParameter("uid", uid);

        String result = (String) query.getSingleResult();

        return result;
    }

    public List<String> getFullData(){
        String select = "select JSON_ARRAY(JSON_OBJECT('id',id, " +
                "'namaWilayah', nama_wilayah, " +
                "'pathUrl', url_path)) as dataJson " +
                "from data_wilayah ";

        Query query = entityManager.createNativeQuery(select);

        List<String> result = query.getResultList();

        return result;
    }

    public List<DataWilayah> getDataAceng(){
        String select = "SELECT * FROM data_wilayah";
        Query query = entityManager.createNativeQuery(select, DataWilayah.class);

        List<DataWilayah> result =  query.getResultList();

        return result;
    }

    private static List<Integer> strToInt(String uid){

        List<Integer> integerList = new ArrayList<>();

        String[] stringArray = uid.split(",");
        for (String stringValue : stringArray) {
            integerList.add(Integer.parseInt(stringValue));
        }
        return integerList;
    }

    private static List<Integer> data(){
        List<Integer> arraydata = Arrays.asList(12, 13, 14, 15);
        System.out.println(arraydata);

        return arraydata;
    }

//    public static void main(String[] args) {
//
//        System.out.println(strToInt("12,13,14,15"));
//    }
}
