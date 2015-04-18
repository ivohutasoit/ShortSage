package com.softhaxi.shortsage.v1.dto;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
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
@Table(name = "T1INMG")
@DiscriminatorValue("INBX")
@NamedQueries({
    @NamedQuery(name = "Inbox.All", query = "from NewInboxMessage a where a.deletedState = 0"),
    @NamedQuery(name = "Inbox.Id", query = "from NewInboxMessage a where a.id = :id")
})
public class InboxMessage extends Message
    implements Serializable {
    
}
