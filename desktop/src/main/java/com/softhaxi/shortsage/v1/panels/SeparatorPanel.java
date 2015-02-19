package com.softhaxi.shortsage.v1.panels;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Hutasoit
 */
public class SeparatorPanel extends JPanel {
    protected Color lColor;
    protected Color rColor;
 
    public SeparatorPanel(Color lColor, Color rColor) {
        this.lColor = lColor;
        this.rColor = rColor;
        setOpaque(false);
    }
 
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(lColor);
        g.drawLine(0, 0, 0, getHeight());
        g.setColor(rColor);
        g.drawLine(1, 0, 1, getHeight());
    }
}
