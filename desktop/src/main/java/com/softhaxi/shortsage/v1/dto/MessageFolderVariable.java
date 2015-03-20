package com.softhaxi.shortsage.v1.dto;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MSFD")
public class MessageFolderVariable extends SystemVariable
        implements Serializable {

}
