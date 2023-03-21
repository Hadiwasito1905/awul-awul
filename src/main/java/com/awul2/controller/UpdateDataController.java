package com.awul2.controller;

import com.awul2.dto.DataWilayahReq;
import com.awul2.query.QueryDataWilayah;
import com.awul2.service.DataWilayahService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author "Noverry Ambo"
 * @start 3/15/2023
 */
@RestController
@RequestMapping("/update")
public class UpdateDataController {

    private final DataWilayahService dataWilayahService;
    private final QueryDataWilayah queryDataWilayah;

    public UpdateDataController(DataWilayahService dataWilayahService, QueryDataWilayah queryDataWilayah) {
        this.dataWilayahService = dataWilayahService;
        this.queryDataWilayah = queryDataWilayah;
    }

}
