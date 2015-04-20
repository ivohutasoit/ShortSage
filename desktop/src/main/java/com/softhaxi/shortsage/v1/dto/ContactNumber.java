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
@AttributeOverrides({
    @AttributeOverride(name = "id",
            column = @Column(name = "CNCNID", unique = true)),
    @AttributeOverride(name = "remark",
            column = @Column(name = "CNRMRK")),
    @AttributeOverride(name = "status",
            column = @Column(name = "CNRCST")),
    @AttributeOverride(name = "createdBy",
            column = @Column(name = "CNCRBY")),
    @AttributeOverride(name = "createdDate",
            column = @Column(name = "CNCRDT")),
    @AttributeOverride(name = "modifiedBy",
            column = @Column(name = "CNMFBY")),
    @AttributeOverride(name = "modifiedDate",
            column = @Column(name = "CNMFDT")),
    @AttributeOverride(name = "deletedState",
            column = @Column(name = "CNDLST")),
    @AttributeOverride(name = "version",
            column = @Column(name = "CNVRSN"))
})
public class ContactNumber extends BasicEntity
    implements Serializable {

    @Column(name = "CNCCID")
    private String person;

    @Column(name = "CNFELD", length = 100)
    private String field;

    @Column(name = "CNMAIN")
    private int main;
}
