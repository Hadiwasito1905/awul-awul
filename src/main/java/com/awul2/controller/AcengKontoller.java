package com.awul2.controller;

import com.awul2.dto.TestPunyaAceng;
import com.awul2.service.AcengService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author "Noverry Ambo"
 * @start 3/21/2023
 */
@RestController
@RequestMapping("/aceng")
public class AcengKontoller {

    @Autowired
    AcengService acengService;

    @GetMapping("/get")
    public ResponseEntity<TestPunyaAceng> getData(@RequestParam Integer tipe){
        return acengService.getDataAceng(tipe);
    }

    @GetMapping("/getfinal")
    public ResponseEntity<TestPunyaAceng> getFinalData(@RequestParam Integer tipe){
        return acengService.getFinalData(tipe);
    }
}
