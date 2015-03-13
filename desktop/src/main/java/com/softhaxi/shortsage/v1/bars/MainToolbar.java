package com.softhaxi.shortsage.v1.bars;

import com.softhaxi.shortsage.v1.stages.MainWindow;
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
        JButton bModem = new JButton("Connect");
        add(bModem);
        bModem.addActionListener(new ActionListener() {
        
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Service.getInstance().getServiceStatus() == ServiceStatus.START) {
                    Service.getInstance().stopService();
                    bModem.setText("Connect");
                    return;
                }
                
                SerialModemGateway gateway = new SerialModemGateway("modem.com1", "COM4", 115200, "", "");
                gateway.setInbound(true);
                gateway.setOutbound(true);
                
                GlobalConstant.getInstance().addGateway("modem.com1", gateway);
                bModem.setText("Disconnect");
            }
        }); 
//        JButton bConnect = new JButton("Connect Modem");
//        add(bConnect);
//        final JButton bNewMsg = new JButton("New Message");
//        bNewMsg.setEnabled(false);
//        bConnect.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                ((JButton)e.getSource()).setVisible(false);
//                bNewMsg.setEnabled(true);
//            }
//        });
//        bNewMsg.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new MessageDialog(host, "New Message", true).setVisible(true);
//            }
//        });
//        add(bNewMsg);
        
        add(Box.createHorizontalGlue());
        add(new JButton("Log Out", new ImageIcon(getClass().getClassLoader().getResource("images/ic_logout.png"))));
    }
}
