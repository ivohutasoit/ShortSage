package com.softhaxi.shortsage.v1.page;

import com.softhaxi.shortsage.v1.dto.ContactPerson;
import com.softhaxi.shortsage.v1.forms.ContactGroupActionForm;
import com.softhaxi.shortsage.v1.forms.ContactPersonActionForm;
import com.softhaxi.shortsage.v1.util.HibernateUtil;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jdesktop.swingx.JXSearchField;

/**
 *
 * @author Hutasoit
 */
public class NewPhoneBookPage extends JPanel
        implements ActionListener {
        
    private JButton bgNew, bgImport, bgExport, bgDelete, bgRefresh;
    private JList dGroup, dContact;
    private JXSearchField fsContact;
    
    private DefaultListModel mGroup, mContact;
    
    public NewPhoneBookPage() {
        super();
        mContact = new DefaultListModel<ContactPerson>();
    }
    
    public void initComponents() {
        JPanel pList = new JPanel(new BorderLayout(10, 0));
        pList.setPreferredSize(new Dimension(500, getHeight()));
        pList.add(getGroupPanel(), BorderLayout.WEST);
        pList.add(getContactPanel(), BorderLayout.CENTER);
        add(pList, BorderLayout.WEST);
        
        add(new ContactPersonActionForm(), BorderLayout.CENTER);
    }
    
    private JPanel getGroupPanel() {
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
        
        return pGroup;
    }
    
    private JPanel getContactPanel() {
        JPanel pContact = new JPanel(new BorderLayout(0, 4));
        pContact.setBorder(new EmptyBorder(2, 2, 2, 2));
        pContact.setBackground(Color.white);
        fsContact = new JXSearchField("Search contact person...");
        fsContact.setSearchMode(JXSearchField.SearchMode.REGULAR);
        fsContact.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Under Construction");
            }
        });
        
        pContact.add(fsContact, BorderLayout.NORTH);
        dContact = new JList();
        pContact.add(new JScrollPane(dContact), BorderLayout.CENTER);
        
        return pContact;
    }
    
    public void initData() {
        readContact();
    }
    
    private void readContact() {
        mContact = new DefaultListModel<ContactPerson>();
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from " + ContactPerson.class.getName());
        for (Object cp : query.list()) {
            mContact.addElement(cp);
        }
        session.close();
        
        dContact.setModel(mContact);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton) {
            JButton source = (JButton) e.getSource();
            if(source == bgNew) {
//                final ContactGroupActionForm form = new ContactGroupActionForm();
//                CDialog dialog = new CDialog(null, form, "New Contact Group", true);
//                try {
//                    Toolkit kit = Toolkit.getDefaultToolkit();
//                    dialog.setIconImage(kit.createImage(ClassLoader.getSystemResource("images/ic_logo.png")));
//                } catch (Exception ex) {
//                    System.err.printf(ex.getMessage());
//                }
//                dialog.setVisible(true);
            }
        }
    }
    
}
