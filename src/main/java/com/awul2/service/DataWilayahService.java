package com.awul2.service;

import com.awul2.dto.DataWilayahReq;
import com.awul2.model.DataWilayah;
import com.awul2.query.QueryDataWilayah;
import com.awul2.repo.DataWilayahRepository;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author "Noverry Ambo"
 * @start 3/11/2023
 */
@Service
@Slf4j
public class DataWilayahService {

    private final DataWilayahRepository dataWilayahRepository;
    private final QueryDataWilayah queryDataWilayah;

    public DataWilayahService(DataWilayahRepository dataWilayahRepository, QueryDataWilayah queryDataWilayah) {
        this.dataWilayahRepository = dataWilayahRepository;
        this.queryDataWilayah = queryDataWilayah;
    }

    public DataWilayah createData(DataWilayahReq data){
        DataWilayah dataWilayah = new DataWilayah();

        dataWilayah.setKodeWilayah(data.getCode());
        dataWilayah.setNamaWilayah(data.getName());
        dataWilayah.setStringDataWil(data.getStrcode());

        return dataWilayahRepository.save(dataWilayah);
    }

    public List<DataWilayah> findAll(){
        return dataWilayahRepository.findAll();
    }

    public String getData(String uid){
        return queryDataWilayah.data(uid);
    }

    public List<Object> getData(){

        String jsonString = queryDataWilayah.getFullData().toString();

//
//        String jsonNih = "[{\"Nama\":\"NOVERRY AMBO\",\"NIK\":\"16022022005MGS\",\"Organisasi\":\"AA\",\"Lvl\":\"AA01010201\",\"Tanggal_Absensi\":\"2022-04-18\",\"Bulan\":\"4\",\"Tahun\":\"2022\",\"Id_Pegawai\":\"15448\",\"Absensi_Status\":\"WFH\"}, {\"Nama\":\"ACENGS\",\"NIK\":\"16022022006MGS\",\"Organisasi\":\"AA\",\"Lvl\":\"AA01010201\",\"Tanggal_Absensi\":\"2022-04-18\",\"Bulan\":\"4\",\"Tahun\":\"2022\",\"Id_Pegawai\":\"15448\",\"Absensi_Status\":\"WFO\"} ]";
//        System.out.println("Cetak inih" + jsonNih);

        Gson gson = new Gson();
        List<Object> objects = gson.fromJson(jsonString, List.class);
        List<Object> newObj = new ArrayList<>();

        for (Object obj : objects) {
            newObj.add(obj);
            System.out.println(obj);
        }

        return newObj;
    }

    public String getJsonData(){

        String jsonString = "[{\"Foto_Masuk_Url\":\"https:\\/\\/ioffice.air.id\\/cdn\\/load\\/2022\\/4\\/AA\\/AA01010201\\/jpg\\/13a3832\",\"Foto_Keluar_Url\":\"https:\\/\\/ioffice.air.id\\/cdn\\/load\\/2022\\/4\\/AA\\/AA01010201\\/jpg\\/297dcaf\"}]";

        String newObj = "";
        try {
            Gson gson = new Gson();
            JSONArray jsonArray = new JSONArray(jsonString);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            newObj = gson.toJson(jsonObject);
            String fotoMasukUrl = jsonObject.getString("Foto_Masuk_Url");
            String fotoKeluarUrl = jsonObject.getString("Foto_Keluar_Url");
            System.out.println(fotoMasukUrl);
            System.out.println(fotoKeluarUrl);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newObj ;
    }

    public Optional<DataWilayah> update(DataWilayahReq req, Long id){

        Optional<DataWilayah> data = dataWilayahRepository.findById(id);

        if (data.isPresent()){
            data.get().setNamaWilayah(req.getName());
            data.get().setStringDataWil(req.getStrcode());
            data.get().setKodeWilayah(req.getCode());
        }

        return data;
    }
}
