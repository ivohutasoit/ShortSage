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
            column = @Column(name = "GLMFDT")),
    @AttributeOverride(name = "deletedState",
            column = @Column(name = "GLDLST")),
    @AttributeOverride(name = "version",
            column = @Column(name = "GLVRSN"))
})
@NamedQueries({
    @NamedQuery(name = "ContactGroupLine.ByGroup", 
            query = "from ContactGroupLine a where a.deletedState = 0 and a.group = :group")
})
public class ContactGroupLine extends BasicEntity
    implements Serializable {
    
    @ManyToOne
    @JoinColumn(name="CCCCID")
    @Column(name = "GLCGID")    
    private ContactGroup group;
    
    @ManyToOne
    @JoinColumn(name="CCCCID")
    @Column(name = "GLCCID")
    private ContactPerson contact;
    
    @Column(name = "GLNMBR")
    private String number;
}
