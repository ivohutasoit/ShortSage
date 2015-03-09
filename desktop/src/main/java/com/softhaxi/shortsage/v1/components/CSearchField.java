package com.softhaxi.shotsage.v1.components;

import java.awt.event.*;
import java.swing.JButton;
import java.swing.JTextField;
import java.swing.JPanel;
/**
 * Search Field Panel customization
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class CSearchField extends JPanel 
        implements ActionListener, KeyListener {
    public interface SearchListener {
      public void doSearch();
    }
    
    private SearchListener listener;
    
    private JButton btnSearch;
    private JTextField txtSearch;
    
    /**
     * Constructor
     */
    public CSearchField() {
        initComponents();
    }
    
    /**
     * Initialize all components
     */
    private void initComponents() {
        setLayout(new BorderLayout(5, 0));
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
    
    /**
     * 
     * @param listener
     */
    public void setSearchListener(SearchListener listener) {
        this.listener = listener;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.listener.doSearch();
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER){
            this.listener.doSearch();
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) { }
}
