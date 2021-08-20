/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.gateways.bussiness.repository;

import com.project.gateways.model.entities.Device;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Rawan.Ahmed
 */
public interface DeviceRepository extends JpaRepository<Device, Long>{
    
    Page<Device> findByGatewayId(Long gatewayId, Pageable pageable);

    Optional<Device> findByIdAndGatewayId(Long id, Long gatewayId);
    
    Optional<Device> findByUid(Long uid);
}
