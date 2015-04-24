package com.softhaxi.shortsage.v1.dto;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
@Entity
@DiscriminatorValue("CGRP")
@NamedQueries({
    @NamedQuery(name = "ContactGroup.All", query = "from ContactGroup a where a.deletedState = 0"),
    @NamedQuery(name = "ContactGroup.Id", query = "from ContactGroup a where a.id = :id")
})
public class ContactGroup extends Contact 
    implements Serializable {
}
