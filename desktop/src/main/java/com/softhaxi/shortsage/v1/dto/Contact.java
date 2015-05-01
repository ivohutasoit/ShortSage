package com.softhaxi.shortsage.v1.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
@Entity
@Table(name = "M0CNTC")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "CCCCTY", 
        discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "CNTC")
@AttributeOverrides({
    @AttributeOverride(name = "id",
            column = @Column(name = "CCCCID", unique = true)),
    @AttributeOverride(name = "remark",
            column = @Column(name = "CCRMRK")),
    @AttributeOverride(name = "status",
            column = @Column(name = "CCRCST")),
    @AttributeOverride(name = "createdBy",
            column = @Column(name = "CCCRBY")),
    @AttributeOverride(name = "createdDate",
            column = @Column(name = "CCCRDT")),
    @AttributeOverride(name = "modifiedBy",
            column = @Column(name = "CCMFBY")),
    @AttributeOverride(name = "modifiedDate",
            column = @Column(name = "CCMFDT")),
    @AttributeOverride(name = "deletedState",
            column = @Column(name = "CCDLST")),
    @AttributeOverride(name = "version",
            column = @Column(name = "CCVRSN"))
})
@NamedQueries({
    @NamedQuery(name = "Contact.ByName", query = "from Contact a where a.name = :name")
})
public class Contact extends BasicEntity
        implements Serializable {
    @Column(name = "CCCCNA", length = 100)
    private String name;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
    private Set<ContactGroupLine> groups = new HashSet<ContactGroupLine>(0);
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    private Set<ContactGroupLine> persons = new HashSet<ContactGroupLine>(0);

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
     * @return the groups
     */
    public Set<ContactGroupLine> getGroups() {
        return groups;
    }

    /**
     * @param groups the groups to set
     */
    public void setGroups(Set<ContactGroupLine> groups) {
        this.groups = groups;
    }

    /**
     * @return the persons
     */
    public Set<ContactGroupLine> getPersons() {
        return persons;
    }

    /**
     * @param persons the persons to set
     */
    public void setPersons(Set<ContactGroupLine> persons) {
        this.persons = persons;
    }
}
