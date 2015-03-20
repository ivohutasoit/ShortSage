package com.softhaxi.shortsage.v1.dto;

@Entity
@Table(name = "S0VARL")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="VAVATY",discriminatorType=DiscriminatorType.STRING)  
@DiscriminatorValue("VARL")
public class SystemVariable implements Serializable {
  protected String id;
  
  protected String name;
  
  protected String value;
  
  protected String param;
  
  protected int status;
  
  protected String createdBy;
  
  protected Date createdOn;
  
  protected String modifiedBy;
  
  protected String modifiedOn;
  
  protected int deletionState;
}
