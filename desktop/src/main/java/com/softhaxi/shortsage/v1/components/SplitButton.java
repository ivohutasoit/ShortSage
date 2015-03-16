package com.softhaxi.shortsage.v1.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
public class SplitButton extends JButton implements ActionListener {

    private JButton bMain;
    private JButton bDropDown;
    private JPopupMenu mPopup;

    public SplitButton() {
        this("");
    }

    public SplitButton(String text) {
        this(new JButton(text), SwingConstants.SOUTH);
    }

    public SplitButton(String text, Icon icon) {
        this(new JButton(text, icon), SwingConstants.SOUTH);
    }

    public SplitButton(JButton button, int direction) {
        this.bMain = button;

        this.bDropDown = new BasicArrowButton(direction);
        this.bDropDown.addActionListener(this);

        this.setBorderPainted(false);
        this.bDropDown.setBorderPainted(false);
        this.bMain.setBorderPainted(false);

        this.setPreferredSize(this.bMain.getPreferredSize());
//        this.setMaximumSize(new Dimension(75, 34));
//        this.setMinimumSize(new Dimension(200, 34));

        this.setLayout(new BorderLayout());
        this.setMargin(new Insets(-3, -3, -3, -3));

        this.add(bMain, BorderLayout.CENTER);
        this.add(bDropDown, BorderLayout.EAST);
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
