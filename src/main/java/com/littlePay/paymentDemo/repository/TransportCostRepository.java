package com.littlePay.paymentDemo.repository;

import com.littlePay.paymentDemo.model.TransportCostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface TransportCostRepository extends JpaRepository<TransportCostModel, Integer> {

    TransportCostModel findTransportCostByPairName(String pairName);

    @Query(value = "SELECT max(pair_cost) from TBL_TRANSPORT_COST where pair_name like %:pairName%", nativeQuery = true)
    Float getTransportCostByPairNameMax(@Param("pairName") String pairName);
}
