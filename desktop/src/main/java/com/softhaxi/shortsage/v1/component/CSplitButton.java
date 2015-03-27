package com.softhaxi.shortsage.v1.component;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicArrowButton;

/**
 *
 * @author Hutasoit
 */
public class CSplitButton extends JButton implements ActionListener {

    private JButton bMain;
    private JButton bDropDown;
    private JPopupMenu mPopup;

    public CSplitButton() {
        this("");
    }

    public CSplitButton(String text) {
        this(new JButton(text), SwingConstants.SOUTH);
    }
    
    public CSplitButton(Icon icon) {
        this(new JButton(icon), SwingConstants.SOUTH);
    }

    public CSplitButton(String text, Icon icon) {
        this(new JButton(text, icon), SwingConstants.SOUTH);
    }

    public CSplitButton(JButton button, int direction) {
        this.bMain = button;

        this.bDropDown = new BasicArrowButton(direction);
        this.bDropDown.addActionListener(this);

        this.setBorderPainted(false);
        this.bDropDown.setBorderPainted(false);
        this.bMain.setBorderPainted(false);

        this.setPreferredSize(button.getSize());
//        this.setMaximumSize(new Dimension(75, 34));
//        this.setMinimumSize(new Dimension(200, 34));
        setLayout(new FlowLayout());
        this.setMargin(new Insets(-3, -3, -3, -3));

        this.add(bMain);
        this.add(bDropDown);
    }

    public void setMenu(JPopupMenu popup) {
        this.mPopup = popup;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.mPopup == null) {
            return;
        }
        if (!mPopup.isVisible()) {
            Point p = this.getLocationOnScreen();
            mPopup.setLocation((int) p.getX(),
                    (int) p.getY() + this.getHeight());
            mPopup.setVisible(true);
        } else {
            mPopup.setVisible(false);
        }
    }

    /**
     * adds a action listener to this button (actually to the left hand side
     * button, and any left over surrounding space. the arrow button will not be
     * affected.
     *
     * @param al ActionListener
     */
    @Override
    public void addActionListener(ActionListener al) {
        this.bMain.addActionListener(al);
        this.addActionListener(al);
    }
}
