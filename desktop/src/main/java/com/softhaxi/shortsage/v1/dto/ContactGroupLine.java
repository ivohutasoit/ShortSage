package com.softhaxi.shortsage.v1.dto;

import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
@Entity
@Table(name = "D0GRLN")
@AttributeOverrides({
    @AttributeOverride(name = "id",
            column = @Column(name = "GGGLID", unique = true)),
    @AttributeOverride(name = "remark",
            column = @Column(name = "GLRMRK")),
    @AttributeOverride(name = "status",
            column = @Column(name = "GLRCST")),
    @AttributeOverride(name = "createdBy",
            column = @Column(name = "GLCRBY")),
    @AttributeOverride(name = "createdDate",
            column = @Column(name = "GLCRDT")),
    @AttributeOverride(name = "modifiedBy",
            column = @Column(name = "GLMFBY")),
    @AttributeOverride(name = "modifiedDate",
            column = @Column(name = "CLMFDT")),
    @AttributeOverride(name = "deletedState",
            column = @Column(name = "CLDLST")),
    @AttributeOverride(name = "version",
            column = @Column(name = "CLVRSN"))
})
public class ContactGroupLine extends BasicEntity
    implements Serializable {
    @Column(name = "CLCCID")
    private String contact;
}
