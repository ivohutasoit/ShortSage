package com.softhaxi.shortsage.v1.dto.base;

/**
 * Definition of base entity on application 
 * <p>Rerefences for this class
 * <ul>
 * <li>http://www.concretepage.com/hibernate/example-mappedsuperclass-hibernate</li>
 * <li>http://stackoverflow.com/questions/20063330/how-to-load-hibernate-cfg-xml-from-different-location</li>
 * </ul>
 * </p>
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.1
 */
@MappedSuperclass
public class BasicEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private String remark;

    private int status;

    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    private String modifiedBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    private int deletedState;

    @Version
    private int version;
    
    /**
     * 
     */ 
    public BasicEntity() {
        this.id = UUID.randomUUID().toString();
        this.status = 1;
        this.createdDate = new Date();
        this.createdBy = "SYSTEM";
        this.modifiedDate = createdDate;
        this.modifiedBy = createdBy;
        this.deletedState = 0;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the modifiedBy
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * @param modifiedBy the modifiedBy to set
     */
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * @return the modifiedDate
     */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     * @param modifiedDate the modifiedDate to set
     */
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * @return the deletedState
     */
    public int getDeletedState() {
        return deletedState;
    }

    /**
     * @param deletedState the deletedState to set
     */
    public void setDeletedState(int deletedState) {
        this.deletedState = deletedState;
    }

    /**
     * @return the version
     */
    public int getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(int version) {
        this.version = version;
    }
}
