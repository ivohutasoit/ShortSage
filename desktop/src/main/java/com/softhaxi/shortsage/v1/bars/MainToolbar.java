package com.softhaxi.shortsage.v1.bars;

import com.softhaxi.shortsage.v1.component.CDialog;
import com.softhaxi.shortsage.v1.component.JSplitButton;
import com.softhaxi.shortsage.v1.component.NewCSplitButton;
import com.softhaxi.shortsage.v1.enums.ServiceHandler;
import com.softhaxi.shortsage.v1.forms.MessageActionForm;
import com.softhaxi.shortsage.v1.stage.MainWindow;
import com.softhaxi.shortsage.v1.task.ModemTask;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingWorker;
import org.smslib.Service;
import org.smslib.Service.ServiceStatus;

/**
 *
 * @author Hutasoit
 */
public class MainToolbar extends JToolBar {

    private MainWindow host;

    public MainToolbar(MainWindow host) {
        initComponents();
        this.host = host;
    }

    private void initComponents() {
        setFloatable(false);
        
        JSplitButton csNew = new JSplitButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_new_24.png")));
        add(csNew);
        
        final JButton bNewMessage = new JButton("New Message");
        add(bNewMessage);
        bNewMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final MessageActionForm form = new MessageActionForm();
                CDialog dialog = new CDialog(null, form, "New Message", true);
                try {
                    Toolkit kit = Toolkit.getDefaultToolkit();
                    dialog.setIconImage(kit.createImage(ClassLoader.getSystemResource("images/ic_logo.png")));
                } catch (Exception ex) {
                    System.err.printf(ex.getMessage());
                }
                dialog.setVisible(true);
            }
        });

        final JButton bModem = new JButton();
        if (Service.getInstance().getServiceStatus() != ServiceStatus.STARTED) {
            bModem.setText("Connect");
        } else {
            bModem.setText("Disonnect");
        }
        add(bModem);
        bModem.addActionListener(new ActionListener() {
            private ModemTask t1 = null;

            @Override
            public void actionPerformed(ActionEvent e) {

                if (Service.getInstance().getServiceStatus() == ServiceStatus.STARTED) {
                    t1 = new ModemTask(ServiceHandler.STOP);
                    host.getStatusBar().getStatusLabel().setText("Stopping Modem Service...");
                    host.getStatusBar().getProgressBar().setIndeterminate(true);
                    host.getStatusBar().getProgressBar().setVisible(true);
                    t1.addPropertyChangeListener(new PropertyChangeListener() {
                        @Override
                        public void propertyChange(PropertyChangeEvent evt) {
                            if (evt.getPropertyName().equals("state")) {
                                if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                                    try {
                                        if (!t1.isCancelled()) {
                                            if (t1.get() == true) {
                                                bModem.setText("Connect");
                                            }
                                            host.getStatusBar().getStatusLabel().setText("Ready");
                                            host.getStatusBar().getProgressBar().setIndeterminate(false);
                                            host.getStatusBar().getProgressBar().setVisible(false);
                                        }
                                    } catch (InterruptedException | ExecutionException ex) {
                                        Logger.getLogger(MainToolbar.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                        }
                    });
                    t1.execute();
                } else {
                    t1 = new ModemTask(ServiceHandler.START);
                    host.getStatusBar().getStatusLabel().setText("Starting Modem Service...");
                    host.getStatusBar().getProgressBar().setIndeterminate(true);
                    host.getStatusBar().getProgressBar().setVisible(true);
                    t1.addPropertyChangeListener(new PropertyChangeListener() {
                        @Override
                        public void propertyChange(PropertyChangeEvent evt) {
                            if (evt.getPropertyName().equals("state")) {
                                if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                                    try {
                                        if (!t1.isCancelled()) {
                                            if (t1.get() == true) {
                                                bModem.setText("Disconnect");
                                            }
                                            host.getStatusBar().getStatusLabel().setText("Ready");
                                            host.getStatusBar().getProgressBar().setIndeterminate(false);
                                            host.getStatusBar().getProgressBar().setVisible(false);
                                        }
                                    } catch (InterruptedException | ExecutionException ex) {
                                        Logger.getLogger(MainToolbar.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                        }
                    });
                    t1.execute();
                }
            }
        });

        add(Box.createHorizontalGlue());
        
        JButton bNotification = new JButton("1",new ImageIcon(getClass().getClassLoader().getResource("images/ic_message_notification.png")));
        bNotification.setIconTextGap(1);
//        add(bNotification);
        add(new JButton("Log Out", new ImageIcon(getClass().getClassLoader().getResource("images/ic_logout.png"))));
    }
}
