package com.softhaxi.shortsage.v1.dto;


/**
 * References 
 * <ul>
 * <li>http://www.concretepage.com/hibernate/example-mappedsuperclass-hibernate</li>
 * <li>http://stackoverflow.com/questions/20063330/how-to-load-hibernate-cfg-xml-from-different-location</li>
 * </ul>
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
@MappedSuperclass
public class BasicEntity implements Serializable {
  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  protected String id;
  
  protected String name;
  
  protected int stateCode;
  
  protected int statusCode;
  
  protected String createdBy;
  
  @Temporal(TemporalType.TIMESTAMP)
  protected Date createdDate;
  
  protected String modifiedBy;
  
  @Temporal(TemporalType.TIMESTAMP)
  protected Date modifiedDate;
  
  protected int deletedState;
  
  @Version
  protected int version;
  
  
}
