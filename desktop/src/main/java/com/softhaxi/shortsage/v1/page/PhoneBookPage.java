package com.softhaxi.shortsage.v1.page;

import com.softhaxi.shortsage.v1.component.CDialog;
import com.softhaxi.shortsage.v1.component.CPanel;
import com.softhaxi.shortsage.v1.component.CSearchField;
import com.softhaxi.shortsage.v1.component.CTextPrompt;
import com.softhaxi.shortsage.v1.forms.ContactGroupActionForm;
import com.softhaxi.shortsage.v1.forms.MessageActionForm;
import com.softhaxi.shortsage.v1.forms.ContactPersonActionForm;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Hutasoit
 */
public class PhoneBookPage extends CPanel
        implements ActionListener {
    
    private JButton bgNew, bgImport, bgExport, bgDelete, bgRefresh;
    
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
        
        JToolBar tbGroup = new JToolBar();
        tbGroup.setBorder(new EmptyBorder(2, 2, 2, 2));
        
        bgNew = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_plus_12.png")));
        bgNew.addActionListener(this);
        tbGroup.add(bgNew);
        tbGroup.add(new JToolBar.Separator());

        bgImport = new JButton("Import", new ImageIcon(getClass().getClassLoader().getResource("images/ic_upload_12.png")));
        tbGroup.add(bgImport);

        bgExport = new JButton("Export", new ImageIcon(getClass().getClassLoader().getResource("images/ic_download_12.png")));
        tbGroup.add(bgExport);
        tbGroup.add(new JToolBar.Separator());

        bgDelete = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_minus_12.png")));
        tbGroup.add(bgDelete);
        
        tbGroup.add(Box.createHorizontalGlue());
        bgRefresh = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_refresh_12.png")));
        tbGroup.add(bgRefresh);
        
        pGroup.add(tbGroup, BorderLayout.NORTH);
        pList.add(pGroup, BorderLayout.WEST);
        
        JPanel pContact = new JPanel(new BorderLayout(0, 4));
        pContact.setBorder(new EmptyBorder(2, 2, 2, 2));
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
        
        add(new ContactPersonActionForm(), BorderLayout.CENTER);
    }
    
    @Override
    public void initData() {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton) {
            JButton source = (JButton) e.getSource();
            if(source == bgNew) {
                final ContactGroupActionForm form = new ContactGroupActionForm();
                CDialog dialog = new CDialog(null, form, "New Contact Group", true);
                try {
                    Toolkit kit = Toolkit.getDefaultToolkit();
                    dialog.setIconImage(kit.createImage(ClassLoader.getSystemResource("images/ic_logo.png")));
                } catch (Exception ex) {
                    System.err.printf(ex.getMessage());
                }
                dialog.setVisible(true);
            }
        }
    }
    
}
