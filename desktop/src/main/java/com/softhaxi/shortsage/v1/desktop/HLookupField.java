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
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class HLookupField extends JPanel {
    private JXTextField tfLookup;
    private JButton bbLookup;
    
    public HLookupField(String promptText) {
        tfLookup = new JXTextField(promptText);
        bbLookup = new JButton("...");
        bbLookup.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/ic_lookup_16.png")));
        
        initComponents();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        bbLookup.setPreferredSize(new Dimension(25, 25));
        add(tfLookup, BorderLayout.CENTER);
        add(bbLookup, BorderLayout.EAST);
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
