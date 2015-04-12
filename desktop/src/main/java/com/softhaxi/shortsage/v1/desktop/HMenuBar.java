package com.softhaxi.shortsage.v1.desktop;

import com.softhaxi.shortsage.v1.enums.PropertyChangeField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import org.jdesktop.swingx.JXSearchField;
import org.smslib.Service;

/**
 *
 * @author Ivo Hutasoit
 * @since 1
 * @verison 1.0.0
 */
public class HMenuBar extends JMenuBar 
        implements ActionListener {

    private static final ResourceBundle RES_GLOBAL = ResourceBundle.getBundle("global");
    /**
     * List of default menu item
     */
    private JMenu mhFile;
    private JMenuItem miNew;
    private JMenuItem miModem;
    private JMenuItem miUser;
    private JMenuItem miExit;

    private JMenu mhEdit;
    private JMenu mhTools;
    private JMenu mhHelp;
    private JXSearchField mtSearch;

    /**
     * 
     */
    public HMenuBar() {
        initComponents();
        initListeners();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Region Initialization">
    /**
     * 
     */
    private void initComponents() {
        setBorder(new EmptyBorder(0, 2, 0, 2));

        mhFile = new JMenu(RES_GLOBAL.getString("label.file"));
        mhFile.setMnemonic(KeyEvent.VK_F);

        miNew = new JMenuItem(RES_GLOBAL.getString("label.new.item"), KeyEvent.VK_N);
        miNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
        miNew.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/ic_new.png")));
        mhFile.add(miNew);

        miModem = new JMenuItem(RES_GLOBAL.getString("label.modem.connect"), KeyEvent.VK_C);
        miModem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_MASK | KeyEvent.SHIFT_MASK));
        miModem.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/ic_modem_connect_16.png")));
        mhFile.add(miModem);

        mhFile.addSeparator();

        miUser = new JMenuItem(RES_GLOBAL.getString("label.user.login"), KeyEvent.VK_L);
        miUser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_MASK));
        miUser.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/ic_login_16.png")));
        mhFile.add(miUser);

        mhFile.addSeparator();

        miExit = new JMenuItem(RES_GLOBAL.getString("label.exit"), KeyEvent.VK_X);
        mhFile.add(miExit);
        add(mhFile);

        mhEdit = new JMenu(RES_GLOBAL.getString("label.edit"));
        mhEdit.setMnemonic(KeyEvent.VK_E);
        add(mhEdit);

        mhTools = new JMenu(RES_GLOBAL.getString("label.tools"));
        mhTools.setMnemonic(KeyEvent.VK_T);
        add(mhTools);

        mhHelp = new JMenu(RES_GLOBAL.getString("label.help"));
        mhHelp.setMnemonic(KeyEvent.VK_H);
        add(mhHelp);

        add(Box.createHorizontalGlue());
        mtSearch = new JXSearchField(RES_GLOBAL.getString("label.search") + " (Ctrl+I)");
        mtSearch.setSearchMode(JXSearchField.SearchMode.REGULAR);
        mtSearch.setColumns(25);
        mtSearch.setMaximumSize(mtSearch.getPreferredSize());
        add(mtSearch);
    }
    
    /**
     * 
     */
    private void initListeners() {
        miModem.addActionListener(this);
    }
    // </editor-fold>

    /**
     * 
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JMenuItem) {
            JMenuItem item = (JMenuItem) e.getSource();
            if(item == miModem) {
                if(Service.getInstance().getServiceStatus() == Service.ServiceStatus.STOPPED) {
                    firePropertyChange(PropertyChangeField.CONNECTING.toString(), false, true);
                    
//                    firePropertyChange(PropertyChangeField.CONNECTING.toString(), true, false);
                }
            }
        }
    }
}
