package com.softhaxi.shortsage.v1.pages;

import com.softhaxi.shortsage.v1.components.CSearchField;
import com.softhaxi.shortsage.v1.panels.ActionContactPersonPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class ContactPersonPage extends JPanel {
    /**
     * Constructor
     */
    public ContactPersonPage() {
        initComponents();
    }
    
    
    private void initComponents() {
        setLayout(new BorderLayout(0, 4));
        setBorder(new EmptyBorder(4, 4, 4, 4));
        
        JPanel pList = new JPanel(new BorderLayout(0, 4));
        pList.setPreferredSize(new Dimension(250, getHeight()));
        pList.setBackground(Color.white);
        pList.add(new CSearchField() {

            @Override
            public void doSearch() {
                
            }
        }, BorderLayout.NORTH);
        
//        String[] items = { "A", "B", "C", "D" };
//        JList<String> pList = new JList<>(items);
//        pList.setPreferredSize(new Dimension(240, getHeight()));
        add(pList, BorderLayout.WEST);
        
        add(new ActionContactPersonPanel(1), BorderLayout.CENTER);
    }
}
