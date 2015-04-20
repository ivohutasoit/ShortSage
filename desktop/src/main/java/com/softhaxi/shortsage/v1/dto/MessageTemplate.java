package com.softhaxi.shortsage.v1.dto;

import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Ivo Hutasoit
 * @since 1
 * @versio 1.0.0
 */
@Entity
@Table(name = "M0MGTL")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "MTMTTY",
        discriminatorType = DiscriminatorType.STRING
)
@DiscriminatorValue(value = "TMPL")
@AttributeOverrides({
    @AttributeOverride(name = "id",
            column = @Column(name = "MTMTID", unique = true)),
    @AttributeOverride(name = "remark",
            column = @Column(name = "MTRMRK")),
    @AttributeOverride(name = "status",
            column = @Column(name = "MTRCST")),
    @AttributeOverride(name = "createdBy",
            column = @Column(name = "MTCRBY")),
    @AttributeOverride(name = "createdDate",
            column = @Column(name = "MTCRDT")),
    @AttributeOverride(name = "modifiedBy",
            column = @Column(name = "MTMFBY")),
    @AttributeOverride(name = "modifiedDate",
            column = @Column(name = "MTMFDT")),
    @AttributeOverride(name = "deletedState",
            column = @Column(name = "MTDLST")),
    @AttributeOverride(name = "version",
            column = @Column(name = "MTVRSN"))
})
@NamedQueries({
    @NamedQuery(name = "MessageTemplate.All", query = "from MessageTemplate a where a.deletedState = 0"),
    @NamedQuery(name = "MessageTemplate.Id", query = "from MessageTemplate a where a.id = :id")
})
public class MessageTemplate extends BasicEntity
        implements Serializable {
   
   @Column(name = "MTMTNA", nullable = false, length = 100)
   private String name;

   @Column(name = "MTTEXT", nullable = false)
   private String text;

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
   
   
}
