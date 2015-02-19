package com.softhaxi.shortsage.v1.bars;

import com.softhaxi.shortsage.v1.panels.SeparatorPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ResourceBundle;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 *
 * @author Hutasoit
 */
public class MainStatusbar extends JPanel {
    ResourceBundle global = ResourceBundle.getBundle("global");
    
    private JPanel lPanel;
    private JPanel rPanel;
    
    public MainStatusbar() {
        initComponents();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(getWidth(), 23));
        
        lPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 3));
        lPanel.setOpaque(false);
        lPanel.add(new JLabel(global.getString("label.ready")));
        add(lPanel, BorderLayout.WEST);
        
        rPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING, 5, 3));
        rPanel.setOpaque(false);
        
        JPanel crPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 0));
//        crPanel.add(new JProgressBar(0, 100));
        crPanel.add(new SeparatorPanel(Color.gray, Color.white));
        crPanel.add(new JLabel(global.getString("copyright.company.name"), JLabel.CENTER));
        crPanel.add(new SeparatorPanel(Color.gray, Color.white));
        crPanel.add(new JLabel(String.format("<HTML><FONT color=\\\"#FF00FF\\\"><U>%s</U></FONT></HTML>", global.getString("copyright.company.url")), JLabel.CENTER));
        rPanel.add(crPanel);
        add(rPanel, BorderLayout.EAST);
    }
    
//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        
//        g.setColor(new Color(156, 154, 140));
//        g.drawLine(0, 0, getWidth(), 0);
//    }
}
