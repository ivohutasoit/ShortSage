package com.softhaxi.shortsage.v1.components;

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
    
    public CDialog(JFrame parent, JPanel panel, String title, boolean modal) {
        super(parent, title, modal);
        add((JPanel) panel);
        pack();
        setLocationRelativeTo(parent);
    }
}
