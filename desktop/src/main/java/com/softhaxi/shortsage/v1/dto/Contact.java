package com.softhaxi.shortsage.v1.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Table(name = "M0CNTC")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "CCCCTY", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "CNTC")
@AttributeOverrides({
    @AttributeOverride(name = "id",
            column = @Column(name = "CCCCID", unique = true)),
    @AttributeOverride(name = "remark",
            column = @Column(name = "CCRMRK")),
    @AttributeOverride(name = "status",
            column = @Column(name = "CCRCST")),
    @AttributeOverride(name = "createdBy",
            column = @Column(name = "CCCRBY")),
    @AttributeOverride(name = "createdDate",
            column = @Column(name = "CCCRDT")),
    @AttributeOverride(name = "modifiedBy",
            column = @Column(name = "CCMFBY")),
    @AttributeOverride(name = "modifiedDate",
            column = @Column(name = "CCMFDT")),
    @AttributeOverride(name = "deletedState",
            column = @Column(name = "CCDLST")),
    @AttributeOverride(name = "version",
            column = @Column(name = "CCVRSN"))
})
public class Contact extends BasicEntity
        implements Serializable {
    @Column(name = "CCCCNA", length = 100)
    private String name;
}
