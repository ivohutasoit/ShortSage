package com.softhaxi.shortsage.v1.dialogs;

import com.softhaxi.shortsage.v1.pages.MessagePage;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Hutasoit
 */
public class MessageDialog extends JDialog {
    private MessagePage page;
    
    /**
     * 
     * @param frame
     * @param title
     * @param modal 
     */
    public MessageDialog(JFrame frame, String title, boolean modal) {
        super(frame, title, modal);
        page = new MessagePage(this);
        add(page);
        pack(); 
        setLocationRelativeTo(frame); 
    }
}
