/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.gateways.controllers;

import com.project.gateways.bussiness.services.GatewayService;
import com.project.gateways.model.entities.Gateway;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Rawan.Ahmed
 */
@RestController
@RequestMapping("/gateways")
public class GatewayController extends BaseController{
    
    private static final Logger logger = LogManager.getLogger(GatewayController.class);
    
    @Autowired
    private GatewayService gatewayService;
    
    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    public ResponseEntity getGatways(Pageable pageable){
        Page<Gateway> pageGateway = null;
        try{
            pageGateway = gatewayService.getGateways(pageable);
            return buildResponseEntity(true, null, (Object) pageGateway, HttpStatus.OK);
        }catch (Exception exception) {
            return buildExceptionResponseEntity(exception);
        }
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity createNewGateway(@Valid @RequestBody Gateway gateway) {
        Gateway obj = null;
        try{
            obj = gatewayService.createNewGateway(gateway);
            return buildResponseEntity(true, null, (Object) obj, HttpStatus.OK);
        }catch (Exception exception) {
            return buildExceptionResponseEntity(exception);
        }
    }
    
    @RequestMapping(value = "/update/{gatewayId}", method = RequestMethod.POST)
    public ResponseEntity updateGateway(@PathVariable Long gatewayId, @Valid @RequestBody Gateway editedGateway) {
        Gateway obj = null;
        try{
            obj = gatewayService.updateGateway(gatewayId, editedGateway);
            return buildResponseEntity(true, null, (Object) obj, HttpStatus.OK);
        }catch (Exception exception) {
            return buildExceptionResponseEntity(exception);
        }
    }
    
    @RequestMapping(value = "/delete/{gatewayId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deletePost(@PathVariable Long gatewayId) {
        String msg = null;
        try{
            msg = gatewayService.deleteGateway(gatewayId);
            return buildResponseEntity(true, null, (Object) msg, HttpStatus.OK);
        }catch (Exception exception) {
            return buildExceptionResponseEntity(exception);
        }
    }
    
}
