/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.gateways.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;

/**
 *
 * @author Rawan.Ahmed
 */
@Entity
@Data
@Table(name = "GATEWAY")
public class Gateway implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private static final String IPV4_ADDRESS_PATTERN = "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "SERIAL_NUMBER",nullable = false)
    private String serialNumber;
    @Column(name = "NAME")
    private String name;
    @Column(name = "IPv4_address")
    //@Pattern(regexp = IPV4_ADDRESS_PATTERN, message = "IP Address should have IPv4 format e.g. '192.168.1.1'")
    private String ipv4Address;
    @OneToMany(mappedBy = "gateway", fetch = FetchType.LAZY)
    private List<Device> deviceList;
}
