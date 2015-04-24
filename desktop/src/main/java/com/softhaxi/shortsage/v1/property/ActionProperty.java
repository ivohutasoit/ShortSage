package com.softhaxi.shortsage.v1.property;

/**
 * Definition of property change among components.
 * Simple usage this component 
 * <em>Loading Data</em>
 * <code>
 * ActionProperty ap1 = new ActionProperty();
 * property.setRun(false);
 *
 * ActionProperty ap2 = new ActionProperty();
 * property.setRun(true);
 * 
 * // Change current property components --> Load was running
 * firePropertyChange(ActionProperty.NAME, ap1, ap2);
 * // Change current property components --> Load was running
 * ap1.setMessage("No Error");
 * ap1.setCode("0000");
 * firePropertyChange(ActionProperty.NAME, ap2, ap1);
 * </code>
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class ActionProperty {
  public static final String NAME = "ActionProperty";
  
  private boolean run;
  private boolean save;
  private boolean close;
  private String code;
  private String message;
  
  
}
