package com.softhaxi.shortsage.v1.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Version;
import org.hibernate.annotations.GenericGenerator;

/**
 * Gateway of modem to be save as data on database.
 * 
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
@Entity
@Table(name = "M0MDGT")
public class Gateway implements Serializable {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "MGMGID", unique = true)
    private String id;
    
    @Column(name="MGMGNA", length = 100, nullable = false)
    private String name;
    
    @Column(name="MGPORT", length = 10, nullable = false)
    private String port;
    
    @Column(name="MGBDRT")
    private int baudRate;
    
    @Column(name="MGMNFR", length = 100)
    private String manufacture;
    
    @Column(name="MGMODL", length = 100)
    private String model;
    
    @Column(name = "MGRCTS")
    private int status;
    
    @Column(name = "MGCRBY", length = 100)
    private String createdBy;
    
    @Column(name = "MGCRON")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createdOn;
    
    @Column(name = "MGMDBY", length = 100)
    private String modifiedBy;
    
    @Column(name = "MGMDON")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date modifiedOn;
    
    @Column(name = "MGDLST")
    private int deleteState;
    
    @Version
    @Column(name="MGVRSN")
    private Integer version;
    
    
    public Gateway() {
        status = 1;
        deleteState = 0;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setUuid(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the port
     */
    public String getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * @return the rate
     */
    public int getBaudRate() {
        return baudRate;
    }

    /**
     * @param baudRate the rate to set
     */
    public void setBaudRate(int baudRate) {
        this.baudRate = baudRate;
    }

    /**
     * @return the manufacture
     */
    public String getManufacture() {
        return manufacture;
    }

    /**
     * @param manufacture the manufacture to set
     */
    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the createdOn
     */
    public Date getCreatedOn() {
        return createdOn;
    }

    /**
     * @param createdOn the createdOn to set
     */
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * @return the modifiedBy
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * @param modifiedBy the modifiedBy to set
     */
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * @return the modifiedOn
     */
    public Date getModifiedOn() {
        return modifiedOn;
    }

    /**
     * @param modifiedOn the modifiedOn to set
     */
    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    /**
     * @return the deleteState
     */
    public int getDeleteState() {
        return deleteState;
    }

    /**
     * @param deleteState the deleteState to set
     */
    public void setDeleteState(int deleteState) {
        this.deleteState = deleteState;
    }

    /**
     * @return the version
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
}
