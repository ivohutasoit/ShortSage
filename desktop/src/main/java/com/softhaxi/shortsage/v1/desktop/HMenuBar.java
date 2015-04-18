package com.softhaxi.shortsage.v1.desktop;

import com.softhaxi.shortsage.v1.enums.PropertyChangeField;
import com.softhaxi.shortsage.v1.forms.MessageActionForm;
import com.softhaxi.shortsage.v1.stage.HostWindow;
import com.softhaxi.shortsage.v1.util.AppUtil;
import com.softhaxi.shortsage.v1.util.ModemUtil;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingWorker;
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
    private JMenuItem miMessage;
    private JMenuItem miConnect;
    private JMenuItem miDisconnect;
    private JMenuItem miUser;
    private JMenuItem miRestart;
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
        initState();
    }

    // <editor-fold defaultstate="collapsed" desc="Region Initialization">
    /**
     *
     */
    private void initComponents() {
        setBorder(new EmptyBorder(0, 2, 0, 2));

        mhFile = new JMenu(RES_GLOBAL.getString("label.file"));
        mhFile.setMnemonic(KeyEvent.VK_F);

        miMessage = new JMenuItem(RES_GLOBAL.getString("label.new.message"), KeyEvent.VK_N);
        miMessage.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
        miMessage.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/ic_new.png")));
        mhFile.add(miMessage);

        miConnect = new JMenuItem(RES_GLOBAL.getString("label.modem.connect"), KeyEvent.VK_C);
        miConnect.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_MASK | KeyEvent.SHIFT_MASK));
        miConnect.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/ic_modem_connect_16.png")));
        //mhFile.add(miConnect);

        miDisconnect = new JMenuItem(RES_GLOBAL.getString("label.modem.disconnect"), KeyEvent.VK_D);
        miDisconnect.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_MASK | KeyEvent.SHIFT_MASK));
        miDisconnect.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/ic_modem_disconnect_16.png")));
        //mhFile.add(miDisconnect);

        mhFile.addSeparator();

        miUser = new JMenuItem(RES_GLOBAL.getString("label.user.login"), KeyEvent.VK_L);
        miUser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_MASK));
        miUser.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/ic_login_16.png")));
        mhFile.add(miUser);

        mhFile.addSeparator();
        
        miRestart = new JMenuItem(RES_GLOBAL.getString("label.restart"), KeyEvent.VK_R);
        miRestart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_MASK | KeyEvent.SHIFT_MASK));
        mhFile.add(miRestart);
        add(mhFile);

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
        miMessage.addActionListener(this);
        miConnect.addActionListener(this);
        miDisconnect.addActionListener(this);
        miRestart.addActionListener(this);
    }

    private void initState() {
        if (Service.getInstance().getServiceStatus() == Service.ServiceStatus.STARTED) {
            miConnect.setVisible(false);
            miDisconnect.setEnabled(true);
            miDisconnect.setVisible(true);
        } else if (Service.getInstance().getServiceStatus() == Service.ServiceStatus.STARTING) {
            miConnect.setEnabled(false);
            miConnect.setVisible(true);
            miDisconnect.setVisible(false);
        } else if (Service.getInstance().getServiceStatus() == Service.ServiceStatus.STOPPING) {
            miDisconnect.setEnabled(false);
            miDisconnect.setVisible(true);
            miConnect.setVisible(false);
        } else {
            miDisconnect.setVisible(false);
            miConnect.setEnabled(true);
            miConnect.setVisible(true);
        }
    }
    // </editor-fold>

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JMenuItem) {
            JMenuItem source = (JMenuItem) e.getSource();
            if (source == miConnect) {
                miConnect.setEnabled(false);
                firePropertyChange(PropertyChangeField.CONNECTING.toString(), false, true);

                final SwingWorker<Boolean, Void> t1 = new SwingWorker<Boolean, Void>() {

                    @Override
                    protected Boolean doInBackground() throws Exception {
                        return ModemUtil.start();
                    }

                    @Override
                    protected void done() {
                        if (!isCancelled()) {

                        }
                        firePropertyChange(PropertyChangeField.CONNECTING.toString(), true, false);
                    }
                };
                t1.addPropertyChangeListener(new PropertyChangeListener() {

                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        firePropertyChange(evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
                        initState();
                    }
                });
                t1.execute();
            } else if (source == miDisconnect) {
                miDisconnect.setEnabled(false);
                firePropertyChange(PropertyChangeField.DISCONNECTING.toString(), false, true);
                final SwingWorker<Boolean, Void> t1 = new SwingWorker<Boolean, Void>() {

                    @Override
                    protected Boolean doInBackground() throws Exception {

                        return ModemUtil.stop();

                    }

                    @Override
                    protected void done() {
                        if (!isCancelled()) {

                        }
                        firePropertyChange(PropertyChangeField.DISCONNECTING.toString(), true, false);
                    }
                };
                t1.addPropertyChangeListener(new PropertyChangeListener() {

                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        firePropertyChange(evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
                        initState();
                    }
                });
                t1.execute();
            } else if (source == miMessage) {
                final JDialog dialog = new JDialog();
                dialog.setModal(true);
                dialog.setTitle(RES_GLOBAL.getString("label.new") + " Message");
                MessageActionForm form = new MessageActionForm();
                form.addPropertyChangeListener(new PropertyChangeListener() {
                    /**
                     *
                     * @param evt
                     */
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        firePropertyChange(evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());

                        if (evt.getPropertyName().equals(PropertyChangeField.SAVING.toString())) {
                            boolean value = (boolean) evt.getNewValue();
                            if (value == false) {
                                dialog.dispose();
                            }
                        }
                    }
                });
                dialog.add(form);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            } else if(source == miRestart) {
                try {
                    AppUtil.restart(new Runnable() {
                        
                        @Override
                        public void run() {
                            JOptionPane.showMessageDialog(null, "Restarting Applicaition!");
                        }
                    });
                } catch (IOException ex) {
                    Logger.getLogger(HMenuBar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
