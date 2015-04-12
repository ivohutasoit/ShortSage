package com.softhaxi.shortsage.v1.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "S0NTFN")
public class SystemNotification 
    implements Serializable {
    @Id
    private String id;
    
    private String message;
    
    private int status;
    
    private String createdBy;
    
    private Date createdOn;
    
    private String modifiedBy;
    
    private Date modifiedOn;
    
    private int deletedState;
    
    @Version
    private int version;
}
