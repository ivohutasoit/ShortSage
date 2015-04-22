package com.softhaxi.shortsage.v1.desktop;

import javax.swing.JButton;
import javax.swing.JPanel;
import org.jdesktop.swingx.JXTextField;

/**
 *
 * @author Ivo Hutasoit
 */
public class HLookupField extends JPanel {
    private JXTextField text;
    private JButton button;
    
    public HLookupField(String promptText) {
        text = new JXTextField(promptText);
        button = new JButton();
    }
}
