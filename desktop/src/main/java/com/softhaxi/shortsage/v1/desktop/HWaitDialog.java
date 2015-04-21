package com.softhaxi.shortsage.v1.desktop;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import net.java.dev.designgridlayout.DesignGridLayout;

/**
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class HWaitDialog extends JDialog {

    private JProgressBar progress;
    private JLabel label;

    /**
     * 
     * @param title 
     */
    public HWaitDialog(String title) {
        setTitle(title);
        setModal(true);
        setResizable(false);
        setPreferredSize(new Dimension(300, 80));
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        
        initComponents();
    }

    private void initComponents() {
        DesignGridLayout layout = new DesignGridLayout(getContentPane());
        progress = new JProgressBar();
        progress.setIndeterminate(true);
        
        layout.row().grid().add(new JLabel("Please Wait..."));
        layout.row().grid().add(progress);
        
        pack();
        setLocationRelativeTo(null);
    }
}
