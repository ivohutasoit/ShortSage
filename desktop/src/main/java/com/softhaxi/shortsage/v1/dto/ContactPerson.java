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

@Entity
@Table(name = "M0CTPR")
public class ContactPerson implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "CPCPID", unique = true)
    private String id;

    @Column(name = "CPCPNA", length = 100)
    private String name;

    @Column(name = "CPJOIN")
    private Date join;

    @Column(name = "CPRCTS")
    private int status;

    @Column(name = "CPCRBY", length = 100)
    private String createdBy;

    @Column(name = "CPCRON")
    private Date createdOn;

    @Column(name = "CPMDBY", length = 100)
    private String modifiedBy;

    @Column(name = "CPMDON")
    private Date modifiedOn;

    @Column(name = "CPDLST")
    private int deleteState;

    @Version
    @Column(name = "CPVRSN")
    private Integer version;
}
