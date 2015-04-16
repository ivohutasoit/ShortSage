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
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(
        name = "MGMGFD",
        discriminatorType = DiscriminatorType.STRING
)
@DiscriminatorValue(value = "MSSG")
@AttributeOverrides({
    @AttributeOverride(name = "id", 
            column = @Column(name = "MGMGID", unique = true)),
    @AttributeOverride(name = "name",
            column = @Column(name = "MGMGNA", nullable = false, length = 100)),
    @AttributeOverride(name = "remark",
            column = @Column(name = "MGRMRK")),
    @AttributeOverride(name = "stateCode",
            column = @Column(name = "MGSTCD")),
    @AttributeOverride(name = "statusCode",
            column = @Column(name = "MGSSCD")),
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
public class ModemMessage extends BasicEntity
        implements Serializable {

    protected String text;

    /**
     * @return the text
     */
    @Column(name = "MGTEXT", nullable = false)
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }
}
