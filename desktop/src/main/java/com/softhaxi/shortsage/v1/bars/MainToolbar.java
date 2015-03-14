package com.softhaxi.shortsage.v1.bars;

import com.softhaxi.shortsage.v1.GlobalConstant;
import com.softhaxi.shortsage.v1.stages.MainWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import org.smslib.SMSLibException;
import org.smslib.Service;
import org.smslib.Service.ServiceStatus;
import org.smslib.modem.SerialModemGateway;

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
        final JButton bModem = new JButton();
        if (Service.getInstance().getServiceStatus() != ServiceStatus.STARTED) {
            bModem.setText("Connect");
        } else {
            bModem.setText("Disonnect");
        }

        add(bModem);
        bModem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (Service.getInstance().getServiceStatus() == ServiceStatus.STARTED) {
                    try {
                        Service.getInstance().stopService();
                        bModem.setText("Connect");
                        return;
                    } catch (SMSLibException | IOException | InterruptedException ex) {
                        Logger.getLogger(MainToolbar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                SerialModemGateway gateway = new SerialModemGateway("modem.com8", "COM8", 115200, "", "");
                gateway.setInbound(true);
                gateway.setOutbound(true);

                GlobalConstant.getInstance().addGateway("modem.com1", gateway);
                if (Service.getInstance().getServiceStatus() == ServiceStatus.STARTED) {
                    bModem.setText("Disconnect");
                }
            }
        });

        add(Box.createHorizontalGlue());
        add(new JButton("Log Out", new ImageIcon(getClass().getClassLoader().getResource("images/ic_logout.png"))));
    }
}
