package com.softhaxi.shortsage.v1.components;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

/**
 * Search Field Panel customization
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public abstract class CSearchField extends JPanel 
        implements ActionListener, KeyListener {
    
    private JButton btnSearch;
    private JTextField txtSearch;
    private boolean showLabel;
    
    /**
     * Constructor
     */
    public CSearchField() {
        this(true);
    }
    
    public CSearchField(boolean showLabel) {
        this.showLabel = showLabel;
        initComponents();
    }
    
    /**
     * Initialize all components
     */
    private void initComponents() {
        setLayout(new BorderLayout(5, 0));
        if(showLabel) 
                add(new JLabel("Search :"), BorderLayout.WEST);
        
        JPanel pField = new JPanel(new BorderLayout());
        pField.setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(1, 4, 1, 4)));
        txtSearch = new JTextField();
        pField.add(txtSearch, BorderLayout.CENTER);
        pField.setBackground(txtSearch.getBackground());
        txtSearch.setBorder(null);
        txtSearch.addKeyListener(this);
        
        btnSearch = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_search.png")));
        btnSearch.setBorderPainted(false);
        btnSearch.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
        btnSearch.setContentAreaFilled(false);
        btnSearch.setFocusPainted(false);
        btnSearch.setOpaque(false);
        btnSearch.addActionListener(this);
        
        pField.add(btnSearch, BorderLayout.EAST);
        add(pField, BorderLayout.CENTER);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        doSearch();
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER){
            doSearch();
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) { }
    
    /**
     * Method to be implements by each page
     */
    public abstract void doSearch();
}
