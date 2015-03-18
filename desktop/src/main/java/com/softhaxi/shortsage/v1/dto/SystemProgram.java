package com.softhaxi.shortsage.v1.dto;

import java.io.Serializable;
import java.util.Date;

public class SystemProgram implements Serializable {

    private String id;
    private String code;
    private String name;
    private String program;
    private String library;
    private int status;
    private Date createdOn;
    private String createdBy;
    private Date modifiedOn;
    private String modifiedBy;
    private int deletedState;
}
