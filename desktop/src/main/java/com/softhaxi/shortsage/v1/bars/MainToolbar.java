package com.softhaxi.shortsage.v1.bars;

import com.softhaxi.shortsage.v1.dialogs.MessageDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;

/**
 *
 * @author Hutasoit
 */
public class MainToolbar extends JToolBar {
    private JFrame host;
    
    public MainToolbar(JFrame host) {
        initComponents();
        this.host = host;
    }
    
    private void initComponents() {
        JButton bConnect = new JButton("Connect Modem");
        add(bConnect);
        final JButton bNewMsg = new JButton("New Message");
        bNewMsg.setEnabled(false);
        bConnect.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ((JButton)e.getSource()).setVisible(false);
                bNewMsg.setEnabled(true);
            }
        });
        bNewMsg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MessageDialog(host, "New Message", true).setVisible(true);
            }
        });
        add(bNewMsg);
        
        add(Box.createHorizontalGlue());
        add(new JButton("Log Out"));
    }
}
