package com.softhaxi.shortsage.v1.dto;

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
