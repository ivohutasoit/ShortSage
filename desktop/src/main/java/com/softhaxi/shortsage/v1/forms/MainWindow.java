package com.softhaxi.shortsage.v1.stages;

import com.softhaxi.shortsage.v1.bars.MainMenubar;
import com.softhaxi.shortsage.v1.bars.MainStatusbar;
import com.softhaxi.shortsage.v1.bars.MainToolbar;
import com.softhaxi.shortsage.v1.pages.DashboardPage;
import com.softhaxi.shortsage.v1.panels.MainMenuPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author Hutasoit
 */
public class MainWindow extends JFrame {

    ResourceBundle global = ResourceBundle.getBundle("global");

    private MainMenubar menus;
    private JPanel cPanel;

    /**
     *
     */
    public MainWindow() {
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Component Inistialization">                          
    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(String.format("%s %s", global.getString("app.name.short"), global.getString("app.version.short")));
        setPreferredSize(new Dimension(800, 500));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        menus = new MainMenubar();
        setJMenuBar(menus);

        add(new MainToolbar(this), BorderLayout.NORTH);
        add(new MainStatusbar(), BorderLayout.SOUTH);
        add(new MainMenuPanel(this), BorderLayout.WEST);

        cPanel = new JPanel(new BorderLayout());
        cPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 0, 0, 5), new EtchedBorder()));
        cPanel.add(new DashboardPage(), BorderLayout.CENTER);
        add(cPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }
    // </editor-fold>   

    public void setCenterPanel(JPanel panel) {
        cPanel.removeAll();
        cPanel.add(panel, BorderLayout.CENTER);
        cPanel.validate();
    }

    /**
     *
     * @param program
     */
    public void setCenterPanel(String program) {
        ClassLoader loader = MainWindow.class.getClassLoader();

        try {
            Class aClass = loader.loadClass(program);
            System.out.println("Class Name: " + aClass.getName());
            JPanel panel = (JPanel) aClass.newInstance();
            setCenterPanel(panel);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
