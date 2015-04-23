package com.softhaxi.shortsage.v1.desktop;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.jdesktop.swingx.JXTextField;

/**
 * http://stackoverflow.com/questions/4330076/joptionpane-showmessagedialog-truncates-jtextarea-message
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class HLookupField<T> extends JPanel {
    private JXTextField tfLookup;
    private JButton bbLookup;
    
    private String promptText;
    private T userData;
    
    public HLookupField(String promptText) {
        initComponents();
    }
    
    private void initComponents() {
        tfLookup = new JXTextField(promptText);
        bbLookup = new JButton("...");
        
        add(tfLookup);
        add(bbLookup);
        
        setBackground(tfLookup.getBackground());
        setBorder(tfLookup.getBorder());
        tfLookup.setBorder(null);
    }
    
    public void setEditable(boolean editable) {
        tfLookup.setEditable(editable);
    }
    
    public void setText(String text) {
        tfLookup.setText(text);
    }
    
    public void addActionListener(ActionListener listener) {
        bbLookup.addActionListener(listener);
    }
}
