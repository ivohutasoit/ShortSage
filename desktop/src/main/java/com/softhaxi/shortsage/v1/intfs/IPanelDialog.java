package com.softhaxi.shortsage.v1.intfs;

import javax.swing.JDialog;

/**
 * Identification that panel has been embedded on a JDialog. 
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public interface IPanelDialog {
    /**
     * Linking up panel and dialog.
     *
     * @param host the dialog that was host fo panel
     */
    public void setDialogHost(JDialog host);
}
