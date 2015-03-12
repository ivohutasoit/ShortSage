package com.softhaxi.shortsage.v1.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Hutasoit
 */
public class Message implements Serializable {
    private String id;
    private String name;
    private String description;
    private String reciever;
    private Date sendDate;
    private String type;
    private int status;
    private Date createdOn;
    private String createdBy;
    private Date modifiedOn;
    private String modifiedBy;
}
