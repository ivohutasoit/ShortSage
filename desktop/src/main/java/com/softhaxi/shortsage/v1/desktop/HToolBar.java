package com.softhaxi.shortsage.v1.desktop;

import java.util.ResourceBundle;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Ivo Hutasoit
 */
public class HToolBar extends JToolBar {
    
    private static final ResourceBundle RES_GLOBAL = ResourceBundle.getBundle("global");

    private JButton biNew;
    private JButton biLogout;

    public HToolBar() {
        setBorder(new EmptyBorder(0, 2, 0, 2));
        
        biNew = new JButton(RES_GLOBAL.getString("label.new.item"),
                new ImageIcon(getClass().getClassLoader().getResource("images/ic_new.png")));
        add(biNew);
        add(Box.createHorizontalGlue());

        biLogout = new JButton(RES_GLOBAL.getString("label.user.logout"),
                new ImageIcon(getClass().getClassLoader().getResource("images/ic_logout.png")));
        add(biLogout);
    }
}
