package com.awul2.controller;

import com.awul2.dto.JsonResponse;
import com.awul2.dto.MerchantResp;
import com.awul2.dto.SearchMerchantReq;
import com.awul2.service.MerchantService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



/**
 * @author "Noverry Ambo"
 * @start 3/16/2023
 */


@RestController
@RequestMapping("/merchant")
public class MerchantContoller {

    private final MerchantService merchantService;

    public MerchantContoller(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @GetMapping({"/search"})
    public ResponseEntity<JsonResponse<Page<MerchantResp>>> pagePagination(@RequestParam(value = "fullName", required = false) String name,
                                                                           @RequestParam(value = "storeName", required = false) String storeName,
                                                                           @RequestParam(value = "phone", required = false) String phone,
                                                                           @RequestParam(value = "email", required = false) String email,
                                                                           @RequestParam(value = "status", required = false) Integer status,
                                                                           Pageable pageable) {
        final SearchMerchantReq req = new SearchMerchantReq(name, storeName, phone, email, status, pageable);
        return new ResponseEntity<>(merchantService.search(req, pageable), HttpStatus.OK);
    }

}
