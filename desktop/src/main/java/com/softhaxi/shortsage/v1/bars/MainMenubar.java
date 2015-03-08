package com.softhaxi.shortsage.v1.bars;

import java.awt.event.KeyEvent;
import java.util.ResourceBundle;
import javax.swing.Box;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 *
 * @author Hutasoit
 */
public class MainMenubar extends JMenuBar {
    ResourceBundle global = ResourceBundle.getBundle("global");
    
    private JMenu mhFile;
    private JMenu mhEdit;
    private JMenu mhTools;
    private JMenu mhHelp;
    private JTextField mtSearch;
    
    private JMenuItem miExit;
    
    public MainMenubar() {
        super();
        initComponents();
    }
    
    private void initComponents() {
        mhFile = new JMenu(global.getString("label.file"));
        mhFile.setMnemonic(KeyEvent.VK_F);
        miExit = new JMenuItem(global.getString("label.exit"), KeyEvent.VK_X);
        miExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.VK_ALT));
        mhFile.add(miExit);
        add(mhFile);
        
        mhEdit = new JMenu(global.getString("label.edit"));
        mhEdit.setMnemonic(KeyEvent.VK_E);
        add(mhEdit);
        
        mhTools = new JMenu(global.getString("label.tools"));
        mhTools.setMnemonic(KeyEvent.VK_T);
        add(mhTools);
        
        mhHelp = new JMenu(global.getString("label.help"));
        mhHelp.setMnemonic(KeyEvent.VK_H);
        add(mhHelp);
        
        add(Box.createHorizontalGlue());
        mtSearch = new JTextField(25);
        mtSearch.setMaximumSize(mtSearch.getPreferredSize());
        add(mtSearch);
    }
    
    public void addListener(String name) {
        
    }
}
