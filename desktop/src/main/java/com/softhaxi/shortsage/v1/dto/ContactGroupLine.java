package com.softhaxi.shortsage.v1.dto;

import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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

    /**
     * @return the group
     */
    public ContactGroup getGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(ContactGroup group) {
        this.group = group;
    }

    /**
     * @return the contact
     */
    public ContactPerson getContact() {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(ContactPerson contact) {
        this.contact = contact;
    }

    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }
    
    
}
