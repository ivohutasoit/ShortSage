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
@Table(name = "D0CTNB")
public class ContactNumber implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "CNCNID", unique = true)
    private String id;

    @Column(name = "CNCPID")
    private String person;

    @Column(name = "CNFELD", length = 100)
    private String field;

    @Column(name = "CNMAIN")
    private int main;

    @Column(name = "CNRCTS")
    private int status;

    @Column(name = "CNCRBY", length = 100)
    private String createdBy;

    @Column(name = "CNCRON")
    private Date createdOn;

    @Column(name = "CNMDBY", length = 100)
    private String modifiedBy;

    @Column(name = "CNMDON")
    private Date modifiedOn;

    @Column(name = "CNDLST")
    private int deleteState;

    @Version
    @Column(name = "CNVRSN")
    private Integer version;
}
