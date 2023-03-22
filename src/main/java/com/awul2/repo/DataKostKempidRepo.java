package com.awul2.repo;

import com.awul2.model.DataKostKempid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author "Noverry Ambo"
 * @start 3/21/2023
 */
@Repository
public interface DataKostKempidRepo extends JpaRepository<DataKostKempid, Long> {

    List<DataKostKempid> findByTypeCompany(Integer tipe);

    @Query(value = "SELECT * from data_kost_kempid GROUP BY type_company", nativeQuery = true)
    List<DataKostKempid> findbyGroup();
    Page<DataKostKempid> findAll(Pageable pageable);
}
