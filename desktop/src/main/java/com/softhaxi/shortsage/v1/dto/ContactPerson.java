package com.softhaxi.shortsage.v1.dto;

@Entity
@Table("M0CTPR")
public class ContactPerson implements Serializable {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "CPCPID", unique = true)
    private String id;
    
    @Column(name = "CPCPNA", length = 100)
    private String name;
    
    @Column(name = "CPJOIN")
    private Date join;
    
    @Column(name = "CPRCTS")
    private int status;
    
    @Column(name = "CPCRBY", length = 100)
    private String createdBy;
    
    @Column(name = "CPCRON")
    private Date createdOn;
    
    @Column(name = "CPMDBY", length = 100)
    private String modifiedBy;
    
    @Column(name = "CPMDON")
    private Date modifiedOn;
    
    @Column(name = "CPDLST")
    private int deleteState;
    
    @Version
    @Column(name="CPVRSN")
    private Integer version;
}
