package com.softhaxi.shortsage.v1.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * References
 * <ul>
 * <li>http://stackoverflow.com/questions/5257921/hibernate-how-override-an-attribute-from-mapped-super-class</li>
 * </ul>
 *
 */
@Entity
@Table(name = "T0MSSG")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "MGMGFD",
        discriminatorType = DiscriminatorType.STRING
)
@DiscriminatorValue(value = "MSSG")
@AttributeOverrides({
    @AttributeOverride(name = "id",
            column = @Column(name = "MGMGID", unique = true)),
    @AttributeOverride(name = "remark",
            column = @Column(name = "MGRMRK")),
    @AttributeOverride(name = "status",
            column = @Column(name = "MGRCST")),
    @AttributeOverride(name = "createdBy",
            column = @Column(name = "MGCRBY")),
    @AttributeOverride(name = "createdDate",
            column = @Column(name = "MGCRDT")),
    @AttributeOverride(name = "modifiedBy",
            column = @Column(name = "MGMFBY")),
    @AttributeOverride(name = "modifiedDate",
            column = @Column(name = "MGMFDT")),
    @AttributeOverride(name = "deletedState",
            column = @Column(name = "MGDLST")),
    @AttributeOverride(name = "version",
            column = @Column(name = "MGVRSN"))
})
@NamedQueries({
    @NamedQuery(name = "Message.ByRefId", query = "from Message a where a.refId = :uuid")
})
public class Message extends BasicEntity
        implements Serializable {
    @Column(name = "MGMGNA", length = 100)
    private String name;

    @Column(name = "MGRFID", unique = true)
    private String refId;
    
    @Column(name = "MGGWID")
    private String gatewayId;
    
    @Column(name = "MGDATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MGCCID", referencedColumnName = "CCCCID", nullable = true, insertable = true, updatable = false)
    private Contact contact;

    @Column(name = "MGNMBR")
    private String number;

    @Column(name = "MGTEXT", nullable = false)
    private String text;
    
    @Column(name = "MGCNTR")
    private String center;
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the refId
     */
    public String getRefId() {
        return refId;
    }

    /**
     * @param refId the refId to set
     */
    public void setRefId(String refId) {
        this.refId = refId;
    }

    /**
     * @return the gatewayId
     */
    public String getGatewayId() {
        return gatewayId;
    }

    /**
     * @param gatewayId the gatewayId to set
     */
    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the contact
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number the contact to set
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the center
     */
    public String getCenter() {
        return center;
    }

    /**
     * @param center the center to set
     */
    public void setCenter(String center) {
        this.center = center;
    }
    
}
