package com.softhaxi.shortsage.v1.page;

import com.softhaxi.shortsage.v1.component.CPanel;
import com.softhaxi.shortsage.v1.component.CSearchField;
import com.softhaxi.shortsage.v1.component.CTextPrompt;
import com.softhaxi.shortsage.v1.forms.NewContactPersonActionForm;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Hutasoit
 */
public class PhoneBookPage extends CPanel {

    public PhoneBookPage() {
        super();
    }
    
    @Override
    public void initComponents() {
        JPanel pList = new JPanel(new BorderLayout(10, 0));
        pList.setPreferredSize(new Dimension(500, getHeight()));
        
        JPanel pGroup = new JPanel(new BorderLayout(0, 4));
        pGroup.setPreferredSize(new Dimension(200, getHeight()));
        pGroup.setBackground(Color.white);
        
        pList.add(pGroup, BorderLayout.WEST);
        
        JPanel pContact = new JPanel(new BorderLayout(0, 4));
        pContact.setBorder(new EmptyBorder(2,2,2,2));
        pContact.setBackground(Color.white);
        CSearchField sField = new CSearchField(false) {
            
            @Override
            public void doSearch() {
                JOptionPane.showMessageDialog(null, "Under Construction");
            }
        };
        CTextPrompt cPrompt = new CTextPrompt("Search Contact", sField.getField());
        pContact.add(sField, BorderLayout.NORTH);
        pList.add(pContact, BorderLayout.CENTER);
        
        add(pList, BorderLayout.WEST);
        
        add(new NewContactPersonActionForm(), BorderLayout.CENTER);
    }

    @Override
    public void initData() {
    
    }
    
}
