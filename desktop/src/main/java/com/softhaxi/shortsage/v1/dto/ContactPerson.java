package com.softhaxi.shortsage.v1.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("CPRN")
@NamedQueries({
    @NamedQuery(name = "ContactPerson.All", query = "from ContactPerson a where a.deletedState = 0"),
    @NamedQuery(name = "ContactPerson.Id", query = "from ContactPerson a where a.id = :id")
})
public class ContactPerson extends Contact 
    implements Serializable {
    
    @Column(name = "CCPREF")
    private String prefix;
    
    @Column(name = "CCFRNA")
    private String firstName;
    
    @Column(name = "CCMDNA")
    private String midName;
    
    @Column(name = "CCLSNA")
    private String lastName;
    
    @Column(name = "CCCNTY")
    private String country;

    @Column(name = "CCJOIN")
    private Date join;
    
    @Column(name = "CCPHNE")
    private String phone;
    
    
    /**
     * @return the prefix
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * @param prefix the prefix to set
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the midName
     */
    public String getMidName() {
        return midName;
    }

    /**
     * @param midName the midName to set
     */
    public void setMidName(String midName) {
        this.midName = midName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }
    
    /**
     * @return the join
     */
    public Date getJoin() {
        return join;
    }

    /**
     * @param join the join to set
     */
    public void setJoin(Date join) {
        this.join = join;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
