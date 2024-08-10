package com.chart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chart.modal.TrafficData;

@Repository
public interface TrafficRepository extends JpaRepository<TrafficData, Long> {
    
}
