/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.gateways.bussiness.repository;

import com.project.gateways.model.entities.Gateway;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Rawan.Ahmed
 */
public interface GatewayRepository  extends JpaRepository<Gateway, Long> {
    
    Optional<Gateway> findBySerialNumber(String serialNumber);
}
