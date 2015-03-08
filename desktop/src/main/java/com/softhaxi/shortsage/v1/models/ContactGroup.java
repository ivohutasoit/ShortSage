package com.softhaxi.shortsage.v1.models;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Hutasoit
 */
public class ContactGroup implements Serializable {
    private String id;
    private String name;
    private String description;
    private int status;
    private Date createdOn;
    private String createdBy;
    private Date modifiedOn;
    private String modifiedBy;
}
