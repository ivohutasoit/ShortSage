package com.softhaxi.shortsage.v1.component;

import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 *
 * @author Hutasoit
 */
public class NewCSplitButton extends JButton implements MouseListener {
    private JButton bMain;
    
    public NewCSplitButton() {}
    public NewCSplitButton(Icon icon) {
        bMain = new JButton(icon);
        bMain.setBorderPainted(false);
        bMain.setContentAreaFilled(false);
        add(bMain);
    }
}
