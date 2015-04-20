package com.softhaxi.shortsage.v1.dto;

import java.io.Serializable;
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
 * Gateway of modem to be save as data on database.
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
@Entity
@Table(name = "M0GTWY")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "GWGWTY",
        discriminatorType = DiscriminatorType.STRING
)
@DiscriminatorValue(value = "MODM")
@AttributeOverrides({
    @AttributeOverride(name = "id",
            column = @Column(name = "GWGWID", unique = true)),
    @AttributeOverride(name = "name",
            column = @Column(name = "GWGWNA", nullable = false, length = 100)),
    @AttributeOverride(name = "remark",
            column = @Column(name = "GWRMRK")),
    @AttributeOverride(name = "status",
            column = @Column(name = "GWRCST")),
    @AttributeOverride(name = "createdBy",
            column = @Column(name = "GWCRBY")),
    @AttributeOverride(name = "createdDate",
            column = @Column(name = "GWCRDT")),
    @AttributeOverride(name = "modifiedBy",
            column = @Column(name = "GWMFBY")),
    @AttributeOverride(name = "modifiedDate",
            column = @Column(name = "GWMFDT")),
    @AttributeOverride(name = "deletedState",
            column = @Column(name = "GWDLST")),
    @AttributeOverride(name = "version",
            column = @Column(name = "GWVRSN"))
})
@NamedQueries({
    @NamedQuery(name = "Gateway.All", query = "from Gateway a where a.deletedState = 0"),
    @NamedQuery(name = "Gateway.Id", query = "from Gateway a where a.id = :id")
})
public class Gateway extends BasicEntity
        implements Serializable {
    
    @Column(name = "GWGWNA", length = 100, nullable = false)
    private String name;
    
    @Column(name = "GWPORT", length = 10, nullable = false)
    private String port;

    @Column(name = "GWBDRT")
    private int baudRate;
    
    @Column(name = "GWMNFR", length = 100)
    private String manufacture;

    @Column(name = "GWMODL", length = 100)
    private String model;
    
    @Column(name = "GWSRAL")
    private String serial;
    
    @Column(name = "GWISMI")
    private String ismi;

    @Column(name = "GWPVDR")
    private String provider;

    @Column(name = "GWNOBL")
    private String numberBalance;

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
     * @return the port
     */
    public String getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * @return the baudRate
     */
    public int getBaudRate() {
        return baudRate;
    }

    /**
     * @param baudRate the baudRate to set
     */
    public void setBaudRate(int baudRate) {
        this.baudRate = baudRate;
    }

    /**
     * @return the manufacture
     */
    public String getManufacture() {
        return manufacture;
    }

    /**
     * @param manufacture the manufacture to set
     */
    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the serial
     */
    public String getSerial() {
        return serial;
    }

    /**
     * @param serial the serial to set
     */
    public void setSerial(String serial) {
        this.serial = serial;
    }

    /**
     * @return the ismi
     */
    public String getIsmi() {
        return ismi;
    }

    /**
     * @param ismi the ismi to set
     */
    public void setIsmi(String ismi) {
        this.ismi = ismi;
    }

    /**
     * @return the provider
     */
    public String getProvider() {
        return provider;
    }

    /**
     * @param provider the provider to set
     */
    public void setProvider(String provider) {
        this.provider = provider;
    }

    /**
     * @return the numberBalance
     */
    public String getNumberBalance() {
        return numberBalance;
    }

    /**
     * @param numberBalance the numberBalance to set
     */
    public void setNumberBalance(String numberBalance) {
        this.numberBalance = numberBalance;
    }
}
