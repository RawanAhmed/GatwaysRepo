/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.gateways.controllers;

import com.project.gateways.exceptions.BusinessException;
import com.project.gateways.model.pojo.ResponsePojo;
import java.util.Arrays;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Rawan.Ahmed
 */
public class BaseController {
    
    protected ResponsePojo buildResponsePojo(boolean iSuccess, String Message, Object data) {
        ResponsePojo responsePojo = new ResponsePojo(iSuccess, Message, data);
        return responsePojo;
    }
    
    protected ResponseEntity buildResponseEntity(boolean iSuccess, String Message, Object data, HttpStatus httpStatus) {
        ResponsePojo responsePojo = buildResponsePojo(iSuccess, Message, data);
        return new ResponseEntity<>(responsePojo, httpStatus);
    }
    
    protected ResponseEntity buildExceptionResponseEntity(Exception exception) {
        String exceptionMessage = exception.getMessage() != null ? exception.getMessage() : "";
        if (exception instanceof BusinessException) {
            BusinessException businessException = (BusinessException) exception;
            if (businessException.getParams() != null && businessException.getParams().length > 0) {
                exceptionMessage = exceptionMessage + "-" + Arrays.toString(businessException.getParams());
            }
        }
        return buildResponseEntity(false, exceptionMessage, null, HttpStatus.OK);
    }
}
