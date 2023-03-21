package com.awul2.controller;

import com.awul2.dto.DataWilayahReq;
import com.awul2.model.DataWilayah;
import com.awul2.query.QueryDataWilayah;
import com.awul2.service.DataWilayahService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonElement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author "Noverry Ambo"
 * @start 3/11/2023
 */
@RestController()
@RequestMapping("/data")
@Slf4j
public class DataWilayahController {

    private final DataWilayahService dataWilayahService;
    private final QueryDataWilayah queryDataWilayah;

    public DataWilayahController(DataWilayahService dataWilayahService, QueryDataWilayah queryDataWilayah) {
        this.dataWilayahService = dataWilayahService;
        this.queryDataWilayah = queryDataWilayah;
    }

    @PostMapping("/add")
    public DataWilayah add(@RequestBody DataWilayahReq dataWilayah){
        return dataWilayahService.createData(dataWilayah);
    }
    @GetMapping("/get")
    public List<DataWilayah> getAll(){
        return queryDataWilayah.getDataAceng();
    }

    @GetMapping("/getq")
    public List<DataWilayah> getByQ(@RequestParam String uid){
        log.info("UIDDD : {}", uid);
        return queryDataWilayah.findByUid(uid);
    }

    @GetMapping("/getdata")
    public String getdata(){
        return dataWilayahService.getData("13");
    }

    @GetMapping("/getjson")
    public List<Object> getAllJson(){
        return dataWilayahService.getData();
    }

    @GetMapping("/jsonget")
    public String getJsonData(){
        return dataWilayahService.getJsonData();
    }
}
