package com.softhaxi.shortsage.v1.component;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

    @Override
    public void mouseClicked(MouseEvent e) {
    
    }

    @Override
    public void mousePressed(MouseEvent e) {
    
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    
    }

    @Override
    public void mouseExited(MouseEvent e) {
    
    }
}
