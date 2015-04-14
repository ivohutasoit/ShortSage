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
@DiscriminatorValue("OTBX")
public class OutboxMessage extends Message 
    implements Serializable {
  
}
