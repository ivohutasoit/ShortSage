package com.softhaxi.shortsage.v1.page;

import com.softhaxi.shortsage.v1.component.CSearchField;
import com.softhaxi.shortsage.v1.forms.ContactPersonActionForm;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * @author Ivo Hutasoit
 * Reference <a href="http://picture-address-book-for-mac.soft32.com/screenshots/">Screen Shot</a>
 * 
 * @since 1
 * @version 1.0.0
 */
public class ContactPage extends JPanel {
    /**
     * Constructor
     */
    public ContactPage() {
        initComponents();
    }
    
    
    private void initComponents() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(4, 4, 4, 4));
        
        JPanel pList = new JPanel(new BorderLayout(0, 4));
        pList.setPreferredSize(new Dimension(250, getHeight()));
        pList.setBackground(Color.white);
        pList.add(new CSearchField(false) {

            @Override
            public void doSearch() {
                
            }
        }, BorderLayout.NORTH);
        
//        String[] items = { "A", "B", "C", "D" };
//        JList<String> pList = new JList<>(items);
//        pList.setPreferredSize(new Dimension(240, getHeight()));
        add(pList, BorderLayout.WEST);
        
        add(new ContactPersonActionForm(), BorderLayout.CENTER);
    }
}
