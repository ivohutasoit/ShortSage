package com.softhaxi.shortsage.v1.panels;

import java.awt.*;
import java.swing.*;
import java.swing.border.*;

/**
 * Reference <a href="http://www.dreamincode.net/forums/topic/339462-swing-top-down-with-gridbaglayout/">Swing Top Down with GridBagLayout</a>
 * @author Ivo Hutasoit
 * @since 1
 * @verson 1.0.0
 */
public class ActionContactPersonPanel extends JPanel {
    
    private JTextField tName;
    private JTextField tAddr1;
    private JTextField tAddr2;
    private JTextField tAddr3;
    //private JCombobox cStatus;
    
    /**
     * Contructor of class
     * @param action Type action such as View, Create, Edit, Delete
     */
    public ActionContactPersonPanel(int action) {
        initComponents();
        applyActionType(action);
    }
  
    private void initComponents() {
        setLayout(new GridBagLayout());
        addGridItem(this, new JLabel("Contact Name :"), 0, 0, 1, 1, GridBagConstraints.EAST);
        addGridItem(this, new JLabel("Address :"), 0, 1, 1, 1, GridBagConstraints.EAST);
        
        tName = new JTextField(20);
        tName.setMinimumSize(new Dimension(200, 20));
        
        tAddr1 = new JTextField(20);
        tAddr1.setMinimumSize(new Dimension(200, 20));
        
        tAddr2 = new JTextField(20);
        tAddr2.setMinimumSize(new Dimension(200, 20));
        
        tAddr3 = new JTextField(20);
        tAddr3.setMinimumSize(new Dimension(200, 20));
        
        addGridItem(panel, tName, 1, 0, 2, 1, GridBagConstraints.WEST);
        addGridItem(panel, tAddr1, 1, 1, 2, 1, GridBagConstraints.WEST);
        addGridItem(panel, tAddr2, 1, 2, 2, 1, GridBagConstraints.WEST);
        addGridItem(panel, tAddr2, 1, 3, 2, 1, GridBagConstraints.WEST);
        
        addGridItem(this, new JLabel("Status :"), 3, 0, 1, 1, GridBagConstraints.EAST);
        addGridItem(this, new JLabel("Handling :"), 3, 1, 1, 1, GridBagConstraints.EAST);
    }
    
    private void applyActionType(int action) {
    }
  
    private void addGridItem(JPanel panel, JComponent component, int x, int y, 
                int width, int height, int align) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        gbc.weightx = 0.5; // a hint on apportioning space
        gbc.weighty = 0.5;
        gbc.insets = new Insets(5, 5, 5, 5);   // padding
        gbc.anchor = align;    // applies if fill is NONE
        gbc.fill = GridBagConstraints.NONE;
        
        panel.add(component, gbc);
    }
}
