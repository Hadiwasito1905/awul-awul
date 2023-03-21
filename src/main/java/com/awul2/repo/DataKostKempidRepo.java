package com.awul2.repo;

import com.awul2.model.DataKostKempid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author "Noverry Ambo"
 * @start 3/21/2023
 */
@Repository
public interface DataKostKempidRepo extends JpaRepository<DataKostKempid, Long> {

    List<DataKostKempid> findByTypeCompany(Integer tipe);

}
