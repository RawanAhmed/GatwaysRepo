/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.gateways.controllers;

import com.project.gateways.bussiness.services.DeviceService;
import com.project.gateways.model.entities.Device;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Rawan.Ahmed
 */
@RestController
@RequestMapping("/devices")
public class DeviceController extends BaseController {
    
    private static final Logger logger = LogManager.getLogger(DeviceController.class);
    
    @Autowired
    private DeviceService deviceService;
    
    @RequestMapping(value = "/{gatewayId}/getdevices", method = RequestMethod.GET)
    public ResponseEntity getDevicesForSpecificGateway(@PathVariable Long gatewayId, Pageable pageable) {
        Page<Device> pageDevice = null;
        try{
            pageDevice = deviceService.getDevicesForSpecificGateway(gatewayId,pageable);
            return buildResponseEntity(true, null, (Object) pageDevice, HttpStatus.OK);
        }catch (Exception exception) {
            return buildExceptionResponseEntity(exception);
        }
    }
    
    @RequestMapping(value = "/{gatewayId}/createdevice", method = RequestMethod.POST)
    public ResponseEntity createDeviceForSpecificGateway(@PathVariable Long gatewayId, @RequestBody Device device) {
        Device obj = null;
        try{
            obj = deviceService.createDeviceForSpecificGateway(gatewayId, device);
            return buildResponseEntity(true, null, (Object) obj, HttpStatus.OK);
        }catch (Exception exception) {
            return buildExceptionResponseEntity(exception);
        }
    }
    
    @RequestMapping(value = "/{gatewayId}/updatedevice/{deviceId}", method = RequestMethod.POST)
    public ResponseEntity updateDeviceForSpecificGateway(@PathVariable Long gatewayId, @PathVariable Long deviceId,@Valid @RequestBody Device editedDevice) {
        Device obj = null;
        try{
            obj = deviceService.updateDeviceForSpecificGateway(gatewayId, deviceId, editedDevice);
            return buildResponseEntity(true, null, (Object) obj, HttpStatus.OK);
        }catch (Exception exception) {
            return buildExceptionResponseEntity(exception);
        }
    }
    
    @RequestMapping(value = "/{gatewayId}/deletedevice/{deviceId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteDeviceForSpecificGateway(@PathVariable Long gatewayId, @PathVariable Long deviceId) {
        String msg = null;
        try{
            msg = deviceService.deleteDeviceForSpecificGateway(gatewayId, deviceId);
            return buildResponseEntity(true, null, (Object) msg, HttpStatus.OK);
        }catch (Exception exception) {
            return buildExceptionResponseEntity(exception);
        }
    }
    
}
