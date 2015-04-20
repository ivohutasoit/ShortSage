package com.softhaxi.shortsage.v1.stage;

import com.softhaxi.shortsage.v1.desktop.HMenuBar;
import com.softhaxi.shortsage.v1.desktop.HMenuPanel;
import com.softhaxi.shortsage.v1.desktop.HStatusBar;
import com.softhaxi.shortsage.v1.desktop.HToolBar;
import com.softhaxi.shortsage.v1.entities.SystemMenu;
import com.softhaxi.shortsage.v1.enums.PropertyChangeField;
import com.softhaxi.shortsage.v1.page.DashboardPage;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

/**
 * Main window of application as top application
 *
 * @author Ivo Hutasoit
 * @since 1
 * @verison 1.0.0
 */
public class HostWindow extends JFrame
        implements WindowListener, PropertyChangeListener {

    private static final ResourceBundle RES_GLOBAL = ResourceBundle.getBundle("global");
    private static HostWindow instance = null;

    private HMenuBar pMenubar;
    private HToolBar pToolbar;
    private HMenuPanel pMenupanel;
    private JPanel pContentpanel;
    private HStatusBar pStatusbar;

    private boolean wRun = false;

    /**
     * Private Constructor without parameters
     */
    private HostWindow() {
        try {
            Toolkit kit = Toolkit.getDefaultToolkit();
            setIconImage(kit.createImage(ClassLoader.getSystemResource("images/ic_logo.png")));
        } catch (Exception ex) {
            System.err.printf(ex.getMessage());
        }

        initComponents();
        initListeners();

        setContentPanel(new DashboardPage());
        setTitle(String.format("%s %s - %s",
                RES_GLOBAL.getString("app.name.short"),
                RES_GLOBAL.getString("app.version.short"),
                "Dashboard"));
    }

    /**
     * Get Instance of HostWindow by using singleton pattern
     *
     * @return Instance HostWindow Singleton
     */
    public static HostWindow getInstance() {
        if (instance == null) {
            instance = new HostWindow();
        }

        return instance;
    }

    // <editor-fold defaultstate="collapsed" desc="Region Inititalization">  
    /**
     * Initialize components of the frame 1. Add menu bar 2. Add tool bar 3. Add
     * left panel contains list of header and detail menu from database 4. Add
     * center panel for panel showed 5. Add status bar
     */
    private void initComponents() {
        setTitle(String.format("%s %s", RES_GLOBAL.getString("app.name.short"), RES_GLOBAL.getString("app.version.short")));
        setPreferredSize(new Dimension(1200, 700));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        pMenubar = new HMenuBar();
        setJMenuBar(pMenubar);

        pToolbar = new HToolBar();
        pToolbar.setFloatable(false);
        add(pToolbar, BorderLayout.NORTH);

        pMenupanel = new HMenuPanel();
        add(pMenupanel, BorderLayout.WEST);

        pContentpanel = new JPanel(new BorderLayout());
        pContentpanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 0, 0, 0), new EtchedBorder()));
        add(pContentpanel, BorderLayout.CENTER);

        pStatusbar = new HStatusBar();
        add(pStatusbar, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Initialize listeners for all components of frame
     */
    private void initListeners() {
        addWindowListener(this);
        pMenupanel.addPropertyChangeListener(this);
        pMenubar.addPropertyChangeListener(this);
    }

    /**
     *
     * @param menu
     */
    private void setContentPanel(SystemMenu menu) {
        ClassLoader loader = HostWindow.class.getClassLoader();
        try {
            pStatusbar.getStatusLabel().setText("Loading page...");
            Class aClass = loader.loadClass(menu.getProgram());
            System.out.println("Class Name: " + aClass.getName());
            JPanel panel = (JPanel) aClass.newInstance();
            setContentPanel(panel);
            pStatusbar.getStatusLabel().setText("Ready");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(HostWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // </editor-fold>   

    // <editor-fold defaultstate="collapsed" desc="Public methods"> 
    /**
     *
     * @param panel
     */
    public void setContentPanel(JPanel panel) {
        pContentpanel.removeAll();
        pContentpanel.add(panel, BorderLayout.CENTER);
        panel.addPropertyChangeListener(this);
        panel.setVisible(true);
        pContentpanel.validate();
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc="Window Listener Implementation">  
    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        if (wRun) {
            return;
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dispose();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
    // </editor-fold>   

    // <editor-fold defaultstate="collapsed" desc="PropertyChangeListener Implementation">  
    /**
     *
     * @param evt
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(PropertyChangeField.LOADING.toString())
                || evt.getPropertyName().equals(PropertyChangeField.CONNECTING.toString())
                || evt.getPropertyName().equals(PropertyChangeField.DISCONNECTING.toString())
                || evt.getPropertyName().equals(PropertyChangeField.SAVING.toString())) {
            boolean value = (boolean) evt.getNewValue();

            if (value == false) {
                pStatusbar.getStatusLabel().setText(RES_GLOBAL.getString("label.ready"));
                pStatusbar.getProgressBar().setIndeterminate(false);
                pStatusbar.getProgressBar().setVisible(false);
                return;
            }

            if (evt.getPropertyName().equals(PropertyChangeField.LOADING.toString())) {
                pStatusbar.getStatusLabel().setText(RES_GLOBAL.getString("label.loading.data"));
            } else if (evt.getPropertyName().equals(PropertyChangeField.CONNECTING.toString())) {
                pStatusbar.getStatusLabel().setText(RES_GLOBAL.getString("label.connecting"));
            } else if (evt.getPropertyName().equals(PropertyChangeField.DISCONNECTING.toString())) {
                pStatusbar.getStatusLabel().setText(RES_GLOBAL.getString("label.disconnecting"));
            } else if (evt.getPropertyName().equals(PropertyChangeField.SAVING.toString())) {
                pStatusbar.getStatusLabel().setText(RES_GLOBAL.getString("label.saving.data"));
            }
            pStatusbar.getProgressBar().setIndeterminate(true);
            pStatusbar.getProgressBar().setVisible(true);
        } else if (evt.getPropertyName().equals(PropertyChangeField.TREEMENU.toString())) {
            SystemMenu menu = (SystemMenu) evt.getNewValue();
            setTitle(String.format("%s %s - %s",
                    RES_GLOBAL.getString("app.name.short"),
                    RES_GLOBAL.getString("app.version.short"),
                    menu.getName()));
            setContentPanel(menu);
        }
    }
    // </editor-fold>   
}
