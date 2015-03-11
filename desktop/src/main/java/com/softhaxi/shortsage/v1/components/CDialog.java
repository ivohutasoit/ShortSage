package com.softhaxi.shortsage.v1.components;

import com.softhaxi.shortsage.v1.intfs.IPanelDialog;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Implementation a generic dialog
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class CDialog extends JDialog {
    
    public CDialog(JFrame parent, IPanelDialog panel, String title, boolean modal) {
        super(parent, title, modal);
        add((JPanel) panel);
        panel.setDialogHost(this);
        pack();
        setLocationRelativeTo(parent);
    }
}
