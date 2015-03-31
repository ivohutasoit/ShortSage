package com.softhaxi.shortsage.v1.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * A simple JPanel with a border and a title Reference
 * <a href="http://www.java2s.com/Code/Java/Swing-Components/AsimpleJPanelwithaborderandatitle.htm">Simple
 * Title Panel</a>
 * <a hreg="http://stackoverflow.com/questions/19172982/java-swing-programming-panel-title">Panel
 * Title</a>
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class CTitledPanel extends JPanel {

    private JPanel pTitle;
    private JLabel lTitle;
    private JPanel pComponent;

    public CTitledPanel(String title, Component component) {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(4,4,4,4));

        lTitle = new JLabel(title, JLabel.LEADING);
        pTitle = new JPanel(new FlowLayout());
        pTitle.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 1));
        pTitle.add(lTitle);
        add(pTitle, BorderLayout.NORTH);

        pComponent = new JPanel();
        add(component, BorderLayout.CENTER);

        setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }
}
