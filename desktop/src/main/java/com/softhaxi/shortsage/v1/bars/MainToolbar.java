package com.softhaxi.shortsage.v1.bars;

import com.softhaxi.shortsage.v1.component.CDialog;
import com.softhaxi.shortsage.v1.forms.MessageActionForm;
import com.softhaxi.shortsage.v1.stage.MainWindow;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

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
        
//        final JButton bModem = new JButton();
//        if (Service.getInstance().getServiceStatus() != ServiceStatus.STARTED) {
//            bModem.setText("Connect");
//        } else {
//            bModem.setText("Disonnect");
//        }
//
//        add(bModem);
//        bModem.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (Service.getInstance().getServiceStatus() == ServiceStatus.STARTED) {
//                    try {
//                        Service.getInstance().stopService();
//                        bModem.setText("Connect");
//                        return;
//                    } catch (SMSLibException | IOException | InterruptedException ex) {
//                        Logger.getLogger(MainToolbar.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//
//                SerialModemGateway gateway = new SerialModemGateway("modem.com8", "COM8", 115200, "", "");
//                gateway.setInbound(true);
//                gateway.setOutbound(true);
//
//                Constant.getInstance().addGateway("modem.com1", gateway);
//                if (Service.getInstance().getServiceStatus() == ServiceStatus.STARTED) {
//                    bModem.setText("Disconnect");
//                }
//            }
//        });

        add(Box.createHorizontalGlue());
        add(new JButton("Log Out", new ImageIcon(getClass().getClassLoader().getResource("images/ic_logout.png"))));
    }
}
