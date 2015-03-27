package com.softhaxi.shortsage.v1.component;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class CStatusBar extends JToolBar {

    ResourceBundle resGlobal = ResourceBundle.getBundle("global");
    Properties resLocal;

    private JLabel lProgress;
    private JLabel lCompany;
    private JLabel lVersion;
    private JLabel lDate;
    private JProgressBar pProgress;

    /**
     * 
     */
    public CStatusBar() {
        this(null);
    }

    /**
     * 
     * @param filename 
     */
    public CStatusBar(String filename) {
        if (filename != null) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(filename);
                resLocal.load(fis);
                fis.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CStatusBar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(CStatusBar.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fis.close();
                } catch (IOException ex) {
                    Logger.getLogger(CStatusBar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        initComponents();
    }

    /**
     * 
     */
    private void initComponents() {
        setPreferredSize(new Dimension(getWidth(), 23));
        setBorder(new EmptyBorder(1, 5, 1, 5));
        setFloatable(false);

        lProgress = new JLabel(resGlobal.getString("label.ready"));
        add(lProgress);
        add(Box.createHorizontalGlue());

        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pProgress = new JProgressBar();
        pProgress.setVisible(false);
        p1.add(pProgress);
        add(p1);
        add(new JToolBar.Separator());

        lCompany = new JLabel(resLocal != null
                ? resLocal.getProperty("company.name", "") : resGlobal.getString("copyright.company.name"));
        add(lCompany);
        add(new JToolBar.Separator());

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        lDate = new JLabel(sdf.format(new Date()));
        add(lDate);
    }

    /**
     * @return the Status Label 
     */
    public JLabel getStatusLabel() {
        return lProgress;
    }

    /**
     * @return the Progress
     */
    public JProgressBar getProgressBar() {
        return pProgress;
    }
}
