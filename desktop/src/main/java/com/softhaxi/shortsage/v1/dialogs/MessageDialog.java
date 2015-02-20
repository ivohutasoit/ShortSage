package com.softhaxi.shortsage.v1.dialogs;

import com.softhaxi.shortsage.v1.pages.MessagePage;
import javax.swing.JDialog;
import javax.swing.JFrame;

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
