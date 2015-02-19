package com.softhaxi.shortsage.v1.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Hutasoit
 */
public class MainMenuPanel extends JPanel 
    implements ActionListener {
    private final ResourceBundle global = ResourceBundle.getBundle("global");
    private static String hCurrent = null;
    
    private JPanel hPanel;
    private JPanel dPanel;
    private JPanel tPanel;
    
    private JLabel dlTitle;
    private JButton hbWorkspace;
    private JButton hbMessaging;
    private JButton hbSetting;
    
    private DefaultMutableTreeNode top;
    private JTree tree;
    private JScrollPane scroller;
    
    public MainMenuPanel() {
        initComponents();
        initDetailPanel(hCurrent);
    }
    
    private void initComponents() {
        setPreferredSize(new Dimension(240, getHeight()));
        setLayout(new BorderLayout(5, 5));
//        Border border = new LineBorder(Color.GRAY, 1);
//        Border margin = new EmptyBorder(2,2,2,2);
//        setBorder(new CompoundBorder(border, margin));
        setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 5, 0, 5), new EtchedBorder()));
        
        dPanel = new JPanel();
        dPanel.setLayout(new BorderLayout());
        dPanel.setBorder(new EmptyBorder(4, 4, 0, 4));
        dlTitle = new JLabel();
        dlTitle.setFont(dlTitle.getFont().deriveFont(Font.BOLD));
        dPanel.add(dlTitle, BorderLayout.NORTH);
        
        tPanel = new JPanel(new BorderLayout());
        tPanel.setBackground(Color.white);
        top = new DefaultMutableTreeNode(dlTitle.getText());
        dPanel.add(tPanel, BorderLayout.CENTER);
        
        add(dPanel, BorderLayout.CENTER);
        
        hPanel = new JPanel(new GridLayout(3, 1, 5, 0));
        hPanel.setPreferredSize(new Dimension(getWidth(), 90));
        hbWorkspace = new JButton(global.getString("label.workspace").toUpperCase());
        hbWorkspace.setHorizontalAlignment(JButton.LEFT);
        hbWorkspace.addActionListener(this);
//        hbWorkspace.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
//        hbWorkspace.setBorderPainted(false);
//        hbWorkspace.setContentAreaFilled(false);
        hPanel.add(hbWorkspace);
        
        hbMessaging = new JButton(global.getString("label.messaging").toUpperCase());
        hbMessaging.setHorizontalAlignment(JButton.LEFT);
        hbMessaging.addActionListener(this);
        hPanel.add(hbMessaging);
        
        hbSetting = new JButton(global.getString("label.setting").toUpperCase());
        hbSetting.setHorizontalAlignment(JButton.LEFT);
        hbSetting.addActionListener(this);
        hPanel.add(hbSetting);
        add(hPanel, BorderLayout.SOUTH);
    }
    
    /**
     * 
     * @param header 
     */
    private void initDetailPanel(String header) {
        if(header == null || header.equalsIgnoreCase(global.getString("label.workspace"))) {
            header = global.getString("label.workspace").toUpperCase();
            dlTitle.setText(header);
        }
        tPanel.removeAll();
        top = new DefaultMutableTreeNode(dlTitle.getText());
        top.add(new DefaultMutableTreeNode("Main Menu"));
        top.add(new DefaultMutableTreeNode("Extension"));
        top.add(new DefaultMutableTreeNode("Setup Menu"));
        tree = new JTree(top);
        tree.setRootVisible(false);
        JScrollPane treeView = new JScrollPane(tree);
        treeView.setBorder(new EmptyBorder(0, 0, 0, 0));
        tPanel.add(treeView, BorderLayout.CENTER);
    }

    /**
     * 
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        dlTitle.setText(((JButton)e.getSource()).getText());
        initDetailPanel(dlTitle.getText());
    }
}
