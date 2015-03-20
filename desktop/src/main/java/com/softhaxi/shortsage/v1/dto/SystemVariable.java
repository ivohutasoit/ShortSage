package com.softhaxi.shortsage.v1.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "S0VARL")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "VAVATY", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("VARL")
public class SystemVariable implements Serializable {

    @Id
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
