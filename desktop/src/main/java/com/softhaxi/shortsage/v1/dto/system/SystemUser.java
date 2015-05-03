package com.softhaxi.shortsage.v1.dto.system;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
@Entity
@Table(name = "S0SUER")
public class SystemUser 
    implements Serializable {
    
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "SSUUID", unique = true)
    private String id;
    
    private String name;
    
    private String shortName;
    
    private String longName;
    
    private String hash;
    
    private String password;
    
    private String email;
    
    private int status;
   
    @Column(name = "SUCRBY", length = 100)
    private String createdBy;

    @Column(name = "SUCRON")
    private Date createdOn;

    @Column(name = "SUMDBY", length = 100)
    private String modifiedBy;

    @Column(name = "SUMDON")
    private Date modifiedOn;

    @Column(name = "SUDLST")
    private int deletedState;

    @Version
    @Column(name = "SUVRSN")
    private Integer version;
}
