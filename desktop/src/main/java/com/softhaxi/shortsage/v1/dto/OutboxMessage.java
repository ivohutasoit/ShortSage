package com.softhaxi.shortsage.v1.dto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
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
@Table(name = "T1INMG")
@DiscriminatorValue("OTBX")
@NamedQueries({
    @NamedQuery(name = "Outbox.All", query = "from OutboxMessage a where a.deletedState = 0"),
    @NamedQuery(name = "Outbox.Id", query = "from OutboxMessage a where a.id = :id")
})
public class OutboxMessage extends Message
        implements Serializable {
    @Column(name = "MGFLCS")
    private String failureCause;
    
    @Column(name = "MGRTCN")
    private int retryCount;
    
    @Column(name = "MGERMG")
    private String errorMessage;

    /**
     * @return the failureCause
     */
    public String getFailureCause() {
        return failureCause;
    }

    /**
     * @param failureCause the failureCause to set
     */
    public void setFailureCause(String failureCause) {
        this.failureCause = failureCause;
    }

    /**
     * @return the retryCount
     */
    public int getRetryCount() {
        return retryCount;
    }

    /**
     * @param retryCount the retryCount to set
     */
    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
