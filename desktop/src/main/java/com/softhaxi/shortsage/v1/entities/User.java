package com.softhaxi.shortsage.v1.entities;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Hutasoit
 */
public class User implements Serializable {
    private String guid;
    private String username;
    private String password;
    private int status;
    private Date createdOn;
    private String createdBy;
    private Date modifiedOn;
    private String modifiedBy;
    private int deleteState;
}
