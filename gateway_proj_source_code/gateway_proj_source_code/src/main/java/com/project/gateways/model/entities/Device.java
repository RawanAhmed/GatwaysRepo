/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.gateways.model.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Table;
import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;

/**
 *
 * @author Rawan.Ahmed
 */
@Entity
@Data
@Table(name = "PERIPHERAL_DEVICE")
public class Device implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "UID",nullable = false)
    private Long uid;
    @Column(name = "VENDOR")
    private String vendor;
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "STATUS")
    //@Pattern(regexp = "^online|offline$", message = "Stauts must have value 'online' or 'offline'")
    private String status;
    @JoinColumn(name = "gateway_id", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Gateway gateway;

}
