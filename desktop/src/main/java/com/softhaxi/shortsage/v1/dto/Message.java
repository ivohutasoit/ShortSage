package com.softhaxi.shortsage.v1.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
public class Message implements Serializable {
  @Id
  @GeneratedValue(generator="system-uuid")
  @GenericGenerator(name="system-uuid", strategy = "uuid")
  @Column(name = "MSSGID", unique = true)
  private String id;
  
  @Column(name = "MSSGCT", length = 100)
  private String contact;
  
  @Column(name = "MSSGGR", length = 100)
  private String group;
  
  @Column(name = "MSTEXT")
  private String message;
  
  @Column(name = "MSDATE")
  private Date date;
  
  @Column(name = "MSFLDR", length = 100)
  private String folder;
  
  @Column(name = "MSRCST")
  private int status;
  
  @Column(name = "MSCRON")
  private Date createdOn;
  
  @Column(name = "MSCRBY", length = 100)
  private String createdBy;
  
  @Column(name = "MSMDON")
  private Date modifiedOn;
  
  @Column(name = "MSMDBY", length = 100)
  private String modifiedBy;
  
  @Column(name = "MSDLST")
  public int deleteState;
  
  @Version
  @Column(name="MGVRSN")
  private Integer version;
}
