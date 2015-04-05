package com.softhaxi.shortsage.v1.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PRSN")
public class ContactPerson extends Contact 
    implements Serializable {

    @Column(name = "CCJOIN")
    private Date join;
    
    @Column(name = "CCPHNE")
    private String phone;

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
