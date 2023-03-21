package com.awul2.service;

import com.awul2.dto.JsonResponse;
import com.awul2.dto.MerchantResp;
import com.awul2.dto.SearchMerchantReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author "Noverry Ambo"
 * @start 3/16/2023
 */
@Service
@Slf4j
public class MerchantService {
    public JsonResponse<Page<MerchantResp>> search(SearchMerchantReq req, Pageable pageable) {
        return null;
    }

}
