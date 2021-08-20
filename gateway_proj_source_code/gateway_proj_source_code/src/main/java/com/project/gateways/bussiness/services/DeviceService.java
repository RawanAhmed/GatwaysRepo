/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.gateways.bussiness.services;

import com.project.gateways.bussiness.repository.DeviceRepository;
import com.project.gateways.bussiness.repository.GatewayRepository;
import com.project.gateways.exceptions.BusinessException;
import com.project.gateways.model.entities.Device;
import com.project.gateways.model.entities.Gateway;
import java.util.ArrayList;
import java.util.Date;
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
public class DeviceService {
    
    private static final Logger logger = LogManager.getLogger(DeviceService.class);
    private static final String STATUS_PATTERN = "^online|offline$";
    private static final Pattern pattern = Pattern.compile(STATUS_PATTERN);
    
    @Autowired
    private GatewayRepository gatewayRepo;
    @Autowired
    private DeviceRepository deviceRepo;
    
    public Page<Device> getDevicesForSpecificGateway(Long gatewayId,Pageable pageable){
        if (!gatewayRepo.existsById(gatewayId)) {
            logger.error("GatewayId " + gatewayId + " not found");
            throw new BusinessException("GatewayId " + gatewayId + " not found");
        }
        return deviceRepo.findByGatewayId(gatewayId, pageable);
    }
    
    public Device createDeviceForSpecificGateway(Long gatewayId,Device device){
        Optional<Gateway> gateway = gatewayRepo.findById(gatewayId);
        if(gateway.isPresent()){
            validateDevice(device);
            device.setGateway(gateway.get());
            device.setCreatedDate(new Date());
            device.getGateway().setDeviceList(new ArrayList<>());
        }else{
            logger.error("GatewayId " + gatewayId + " not found");
            throw new BusinessException("GatewayId " + gatewayId + " not found");
        }
        return deviceRepo.save(device);
    }
    
    public Device updateDeviceForSpecificGateway(Long gatewayId,Long deviceId,Device editedDdevice){
        Device deviceObj = null;
        Optional<Device> device = deviceRepo.findByIdAndGatewayId(deviceId, gatewayId);
        if(device.isPresent()){
            deviceObj = device.get();
            validateDevice(editedDdevice);
            if(editedDdevice != null && editedDdevice.getUid() != null){
                deviceObj.setUid(editedDdevice.getUid());
            }
            if(editedDdevice != null && editedDdevice.getVendor() != null){
                deviceObj.setVendor(editedDdevice.getVendor());
            }
            if(editedDdevice != null && editedDdevice.getStatus() != null){
                deviceObj.setStatus(editedDdevice.getStatus());
            }
        }else{
            logger.error("Device not found with id " + deviceId + " and gateway id " + gatewayId);
            throw new BusinessException("Device not found with id " + deviceId + " and gateway id " + gatewayId);
        }
        return deviceRepo.save(deviceObj);
    } 
    
    public String deleteDeviceForSpecificGateway(Long gatewayId,Long deviceId){
        try{
            Optional<Device> device = deviceRepo.findByIdAndGatewayId(deviceId, gatewayId);
            if(device.isPresent()){
               Device deviceObj = device.get();
               deviceRepo.delete(deviceObj);
               return "The device has been successfully deleted";
            }else{
               throw new BusinessException("Device not found with id " + deviceId + " and gateway id " + gatewayId);
            }
            
        }catch(Exception ex){
            logger.error("Device not found with id " + deviceId + " and gateway id " + gatewayId,ex);
            return "The device can't be deleted";
        }
    }
    
    private void validateDevice(Device device){
        //check uid is unique in database
        if(device.getUid() != null){
            Optional<Device> obj = deviceRepo.findByUid(device.getUid());
            if(obj.isPresent()){
                throw new BusinessException("UID  " + device.getUid()+ " must be unique");
            }
        }
        //validate status to be offline or online only
        if(device.getStatus() != null){
            if(!isValid(device.getStatus())){
                 throw new BusinessException("Stauts must have value 'online' or 'offline'");
            }
        }
    }
    
    private  boolean isValid(String ip) {
        Matcher matcher = pattern.matcher(ip);
        return matcher.matches();
    }
}
