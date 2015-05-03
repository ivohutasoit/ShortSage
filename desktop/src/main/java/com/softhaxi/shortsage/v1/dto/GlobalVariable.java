package com.softhaxi.shortsage.v1.dto;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
@Entity
@Table(name = "S0VARI")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "VAVATY",
        discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "VARI")
@AttributeOverrides({
    @AttributeOverride(name = "id",
            column = @Column(name = "VAVAID", unique = true)),
    @AttributeOverride(name = "remark",
            column = @Column(name = "VARMRK")),
    @AttributeOverride(name = "status",
            column = @Column(name = "VARCST")),
    @AttributeOverride(name = "createdBy",
            column = @Column(name = "VACRBY")),
    @AttributeOverride(name = "createdDate",
            column = @Column(name = "VACRDT")),
    @AttributeOverride(name = "modifiedBy",
            column = @Column(name = "VAMFBY")),
    @AttributeOverride(name = "modifiedDate",
            column = @Column(name = "VAMFDT")),
    @AttributeOverride(name = "deletedState",
            column = @Column(name = "VADLST")),
    @AttributeOverride(name = "version",
            column = @Column(name = "VAVRSN"))
})
@NamedQueries({
    @NamedQuery(name = "GlobalVariable.All", query = "from GlobalVariable a where a.deletedState = 0"),
    @NamedQuery(name = "GlobalVariable.ByRef", query = "from GlobalVariable a where a.reference = :reference"),
    @NamedQuery(name = "GlobalVariable.ByName", query = "from GlobalVariable a where a.name = :name")
})
public class GlobalVariable extends BasicEntity {

    @Column(name = "VAREFC", length = 100)
    protected String reference;

    @Column(name = "VAINDX", length = 100)
    protected String index;

    @Column(name = "VAVANA", length = 100)
    protected String name;

    @Column(name = "VDETL", length = 100)
    protected String detail;
}
