/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.gateways.bussiness.services;

import com.project.gateways.bussiness.repository.GatewayRepository;
import com.project.gateways.exceptions.BusinessException;
import com.project.gateways.model.entities.Gateway;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Rawan.Ahmed
 */
@Service
public class GatewayService {
    
    private static final Logger logger = LogManager.getLogger(GatewayService.class);
    private static final String IPV4_ADDRESS_PATTERN = "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
    private static final Pattern pattern = Pattern.compile(IPV4_ADDRESS_PATTERN);

    @Autowired
    private GatewayRepository gatewayRepo;
    
    public Page<Gateway> getGateways(Pageable pageable){
        return gatewayRepo.findAll(pageable);
    }
    
    public Gateway createNewGateway(Gateway gateway){
        validateGateway(gateway);
        return gatewayRepo.save(gateway);
    }
    
    public Gateway updateGateway(Long gatewayId,Gateway editedGateway){
        Gateway gatewayObj = null;
        Optional<Gateway> gateway = gatewayRepo.findById(gatewayId);
        if(gateway.isPresent()){
            gatewayObj = gateway.get();
            validateGateway(editedGateway);
            if(editedGateway != null && editedGateway.getSerialNumber() != null){
                gatewayObj.setSerialNumber(editedGateway.getSerialNumber());
            }
            if(editedGateway != null && editedGateway.getIpv4Address() != null){
                gatewayObj.setIpv4Address(editedGateway.getIpv4Address());
            }
            if(editedGateway != null && editedGateway.getName() != null){
                gatewayObj.setName(editedGateway.getName());
            }
        }else{
            logger.error("Gateway id " + gatewayId + " not found");
            throw new BusinessException("Gateway id " + gatewayId + " not found");
        }
        return gatewayRepo.save(gatewayObj);
    }
    
    public String deleteGateway(Long gatewayId){
        try{
            Optional<Gateway> gateway = gatewayRepo.findById(gatewayId);
            if(gateway.isPresent()){
               Gateway gatewayObj = gateway.get();
               gatewayRepo.delete(gatewayObj);
               return "The gateway has been successfully deleted";
            }else{
               throw new BusinessException("Gateway id " + gatewayId + " not found");
            }
           
        }catch(Exception ex){
            logger.error("Gateway id " + gatewayId + " not found",ex);
            return "The gateway can't be deleted";
        }
    }
    
    private void validateGateway(Gateway gateway){
        //check serail number is unique in database
        if(gateway.getSerialNumber() != null){
            Optional<Gateway> obj = gatewayRepo.findBySerialNumber(gateway.getSerialNumber());
            if(obj.isPresent()){
                throw new BusinessException("Serial Number " + gateway.getSerialNumber()+ " must be unique");
            }
        }
        //validate IP address 
        if(gateway.getIpv4Address() != null){
            if(!isValid(gateway.getIpv4Address())){
                 throw new BusinessException("IP Address should have IPv4 format e.g. '192.168.1.1'");
            }
        }
    }
    
    private  boolean isValid(String ip) {
        Matcher matcher = pattern.matcher(ip);
        return matcher.matches();
    }
    
}
