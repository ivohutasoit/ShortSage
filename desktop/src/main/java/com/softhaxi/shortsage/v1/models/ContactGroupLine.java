package com.softhaxi.shortsage.v1.models;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Hutasoit
 */
public class ContactGroupLine implements Serializable {
    private String id;
    private String group;
    private String person;
    private String number;
    private int status;
    private Date createdOn;
    private String createdBy;
    private Date modifiedOn;
    private String modifiedBy;
}
