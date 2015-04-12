package com.softhaxi.shortsage.v1.desktop;

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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class HStatusBar extends JToolBar {
    
    private static final ResourceBundle RES_GLOBAL = ResourceBundle.getBundle("global");
    
    private Properties resLocal;
    
    private JLabel lStatus;
    private JLabel lCompany;
    private JLabel lVersion;
    private JLabel lDate;
    private JProgressBar pProgress;
    
    /**
     * 
     */
    public HStatusBar() {
        this(null);
    }

    /**
     * 
     * @param filename 
     */
    public HStatusBar(String filename) {
        if (filename != null) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(filename);
                resLocal.load(fis);
                fis.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(HStatusBar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(HStatusBar.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fis.close();
                } catch (IOException ex) {
                    Logger.getLogger(HStatusBar.class.getName()).log(Level.SEVERE, null, ex);
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

        lStatus = new JLabel(RES_GLOBAL.getString("label.ready"));
        add(lStatus);
        add(Box.createHorizontalGlue());

        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pProgress = new JProgressBar();
        pProgress.setVisible(false);
        p1.add(pProgress);
        add(p1);
        addSeparator();
        add(Box.createHorizontalStrut(25));
        lCompany = new JLabel(resLocal != null
                ? resLocal.getProperty("company.name", "") : RES_GLOBAL.getString("copyright.company.name"));
        add(lCompany);
        add(Box.createHorizontalStrut(25));
        addSeparator();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        lDate = new JLabel(sdf.format(new Date()));
        add(lDate);
    }

    /**
     * @return the Status Label 
     */
    public JLabel getStatusLabel() {
        return lStatus;
    }

    /**
     * @return the Progress
     */
    public JProgressBar getProgressBar() {
        return pProgress;
    }
}
