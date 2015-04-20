package com.softhaxi.shortsage.v1.dto;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
@Entity
<<<<<<< HEAD
@DiscriminatorValue("CGRP")
=======
@DiscriminatorValue("GCRP")
>>>>>>> origin/develop
@NamedQueries({
    @NamedQuery(name = "ContactGroup.All", query = "from ContactGroup a where a.deletedState = 0"),
    @NamedQuery(name = "ContactGroup.Id", query = "from ContactGroup a where a.id = :id")
})
public class ContactGroup extends Contact 
    implements Serializable {
    
    
}
