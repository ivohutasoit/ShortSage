package com.softhaxi.shortsage.v1.dto;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
@Entity
@DiscriminatorValue("GCRP")
@NamedQueries({
    @NamedQuery(name = "ContactGroup.All", query = "from ContactGroup a where a.deletedState = 0"),
    @NamedQuery(name = "ContactGroup.Id", query = "from ContactGroup a where a.id = :id")
})
public class ContactGroup extends Contact 
    implements Serializable {
    
    
}
