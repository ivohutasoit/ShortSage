package com.softhaxi.shortsage.v1.dto;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
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
public class NewInboxMessage extends ModemMessage
    implements Serializable {
    
}
