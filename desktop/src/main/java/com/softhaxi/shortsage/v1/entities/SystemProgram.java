package com.softhaxi.shortsage.v1.entities;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Hutasoit
 */
public class SystemProgram implements Serializable {
    private String guid;
    private String id;
    private String name;
    private String url;
    private int status;
    private Date createdOn;
    private String createdBy;
    private Date modifiedOn;
    private String modifiedBy;
    private int deletedState;
}
