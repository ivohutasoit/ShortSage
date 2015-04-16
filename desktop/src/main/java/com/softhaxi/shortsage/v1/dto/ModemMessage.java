package com.softhaxi.shortsage.v1.dto;

/**
 * References  
 * <ul>
 * <li>http://stackoverflow.com/questions/5257921/hibernate-how-override-an-attribute-from-mapped-super-class</li>
 * </ul>
 *
 */
@Entity
@Table(name = "T0MSSG")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="MGMGFD",
    discriminatorType=DiscriminatorType.STRING
)
@DiscriminatorValue(value="MSSG")
@AttributeOverrides(
  @AttributeOverride(name="id",column=@Column(name="MGMGID", unique=true)),
  @AttributeOverride(name="name",column=@Column(name="MGMGNA", not-null=true)),
  @AttributeOverride(name="remark",column=@Column(name="MGRMRK"))
)

public class ModemMessage extends BasicEntity {
  
}
