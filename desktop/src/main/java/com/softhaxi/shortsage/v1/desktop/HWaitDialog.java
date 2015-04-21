package com.softhaxi.shortsage.v1.desktop;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class HWaitDialog extends JDialog {

    private JProgressBar progress;
    private JLabel label;

    public HWaitDialog(String title) {
        setTitle(title);
        setPreferredSize(new Dimension(300, 50));
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.weightx = 1;
        gbc.gridy = 0;
        add(new JLabel("Please wait..."), gbc);
        progress = new JProgressBar();
        progress.setIndeterminate(true);
        gbc.gridy = 1;
        add(progress, gbc);
        pack();
        setLocationRelativeTo(null);
    }
}
