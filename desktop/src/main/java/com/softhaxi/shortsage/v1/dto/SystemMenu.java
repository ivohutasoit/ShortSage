package com.softhaxi.shortsage.v1.dto;

@Entity
@Table("S1MENU")
public class SystemMenu implements Serializable {

  private String id;
  private String shortName;
  private String longName;
  private int level; // GROUP (Show as button on left bottom window), HEADER, MENU
  private String icon;
  private String parent; // default parent
  private String program; // Form SystemProgram default null
  private String code;
  private String parameter;
  private int status;
  private Date createdOn;
  private String createdBy;
  private Date modifiedOn;
  private String modifiedBy;
  private int deleteState;
}
