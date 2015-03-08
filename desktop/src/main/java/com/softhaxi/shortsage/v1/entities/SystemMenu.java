package com.softhaxi.shortsage.v1.entities;

import com.softhaxi.shortsage.v1.enums.MenuLevel;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Hutasoit
 */
public class SystemMenu implements Serializable {    
    private String guid;
    private String key;
    private String title;
    private String name;
    private int level;
    private String icon;
    private String parent;
    private String program;
    private String parameter;
    private int status;
    private Date createdOn;
    private String createdBy;
    private Date modifiedOn;
    private String modifiedBy;
    private int deleteState;
    
    private boolean keyShow = false;
    
    public SystemMenu() {
        
    }

    public SystemMenu(String key, String title, String name, String program, boolean keyShow) {
        this.key = key;
        this.title = title;
        this.name = name;
        this.program = program;
        this.keyShow = keyShow;
    }

    public SystemMenu(String key, String title, String name) {
        this.key = key;
        this.title = title;
        this.name = name;
        this.level = MenuLevel.ZERO_LEVEL.getValue();
        this.parent = null;
        this.program = null;
    }

    public SystemMenu(String key, String title, String name, int level, 
            String icon, String parent, String program, int status, 
            Date createdOn, String createdBy, Date modifiedOn, String modifiedBy, 
            int deleteState) {
        this.key = key;
        this.title = title;
        this.name = name;
        this.level = level;
        this.icon = icon;
        this.parent = parent;
        this.program = program;
        this.status = status;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.modifiedOn = modifiedOn;
        this.modifiedBy = modifiedBy;
        this.deleteState = deleteState;
    }

    /**
     * @return the guid
     */
    public String getGuid() {
        return guid;
    }

    /**
     * @param guid the guid to set
     */
    public void setGuid(String guid) {
        this.guid = guid;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return the parent
     */
    public String getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(String parent) {
        this.parent = parent;
    }

    /**
     * @return the program
     */
    public String getProgram() {
        return program;
    }

    /**
     * @param program the program to set
     */
    public void setProgram(String program) {
        this.program = program;
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
     * @return the createdOn
     */
    public Date getCreatedOn() {
        return createdOn;
    }

    /**
     * @param createdOn the createdOn to set
     */
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
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
     * @return the modifiedOn
     */
    public Date getModifiedOn() {
        return modifiedOn;
    }

    /**
     * @param modifiedOn the modifiedOn to set
     */
    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
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
     * @return the deleteState
     */
    public int getDeleteState() {
        return deleteState;
    }

    /**
     * @param deleteState the deleteState to set
     */
    public void setDeleteState(int deleteState) {
        this.deleteState = deleteState;
    }
    
    
    /**
     * 
     * @param keyShow 
     */
    public void setKeyShow(boolean keyShow) {
        this.keyShow = keyShow;
    }
    
    /**
     * 
     * @return 
     */
    public boolean isKeyShow() {
        return this.keyShow;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        if(isKeyShow())
            return getKey() + " - " + getName();
        else
            return getName();
    }
}
