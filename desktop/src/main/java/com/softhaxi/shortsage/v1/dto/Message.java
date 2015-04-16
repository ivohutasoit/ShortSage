package com.softhaxi.shortsage.v1.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Version;
import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
@Entity
@Table(name = "T0MSSG")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "MGMGFD", discriminatorType = DiscriminatorType.STRING)
public class Message implements Serializable {
  @Id
  @GeneratedValue(generator="system-uuid")
  @GenericGenerator(name="system-uuid", strategy = "uuid")
  @Column(name = "MGMGID", unique = true)
  protected String id;
  
  @Column(name = "MGCPID", length = 100)
  protected String contact;
  
  @Column(name = "MGCGID", length = 100)
  protected String group;
  
  @Column(name = "MSTEXT")
  protected String text;
  
  @Column(name = "MSDATE")
  protected Date date;
  
  @Column(name = "MSFLDR", length = 100)
  protected String folder;
  
  @Column(name = "MSRCST")
  protected int status;
  
  @Column(name = "MSCRON")
  protected Date createdOn;
  
  @Column(name = "MSCRBY", length = 100)
  protected String createdBy;
  
  @Column(name = "MSMDON")
  protected Date modifiedOn;
  
  @Column(name = "MSMDBY", length = 100)
  protected String modifiedBy;
  
  @Column(name = "MSDLST")
  protected int deleteState;
  
  @Version
  @Column(name="MGVRSN")
  protected Integer version;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * @return the group
     */
    public String getGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * @return the text of message
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text of message to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the folder
     */
    public String getFolder() {
        return folder;
    }

    /**
     * @param folder the folder to set
     */
    public void setFolder(String folder) {
        this.folder = folder;
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
