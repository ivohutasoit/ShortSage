package com.softhaxi.shortsage.v1.dto;

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
 * @versio 1.0.0
 */
@Entity
@Table(name = "M0MGTL")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "MTMTTY",
        discriminatorType = DiscriminatorType.STRING
)
@DiscriminatorValue(value = "TMPL")
@AttributeOverrides({
    @AttributeOverride(name = "id",
            column = @Column(name = "MTMTID", unique = true)),
    @AttributeOverride(name = "name",
            column = @Column(name = "MTMTNA", nullable = false, length = 100)),
    @AttributeOverride(name = "remark",
            column = @Column(name = "MTRMRK")),
    @AttributeOverride(name = "status",
            column = @Column(name = "MTRCST")),
    @AttributeOverride(name = "createdBy",
            column = @Column(name = "MTCRBY")),
    @AttributeOverride(name = "createdDate",
            column = @Column(name = "MTCRDT")),
    @AttributeOverride(name = "modifiedBy",
            column = @Column(name = "MTMFBY")),
    @AttributeOverride(name = "modifiedDate",
            column = @Column(name = "MTMFDT")),
    @AttributeOverride(name = "deletedState",
            column = @Column(name = "MTDLST")),
    @AttributeOverride(name = "version",
            column = @Column(name = "MTVRSN"))
})
@NamedQueries({
    @NamedQuery(name = "MessageTemplate.All", query = "from MessageTemplate a where a.deletedState = 0"),
    @NamedQuery(name = "MessageTemplate.Id", query = "from MessageTemplate a where a.id = :id")
})
public class MessageTemplate extends BasicEntity
        implements Serializable {
}
