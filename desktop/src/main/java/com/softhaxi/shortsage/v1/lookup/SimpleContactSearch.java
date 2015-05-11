package com.softhaxi.shortsage.v1.lookup;

import com.softhaxi.shortsage.v1.dto.Contact;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import org.jdesktop.swingx.JXSearchField;

/**
 * http://www.macs.hw.ac.uk/cs/java-swing-guidebook/?name=JList&page=3
 * 
 * http://stackoverflow.com/questions/13620894/gridbag-layout-problems
 * 
 * http://stackoverflow.com/questions/26063639/jlist-control-size-changes-while-running-the-jar-file%5C
 * 
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class SimpleContactSearch extends JPanel 
        implements ActionListener {

    private static final ResourceBundle RES_GLOBAL = ResourceBundle.getBundle("global");

    private JComboBox cfCategory;
    private JXSearchField sfKeyword;
    private JList ldLeft, ldRight;
    private JButton bOK, bCancel, bClear,
        bSelect, bDeselect;
    
    private DefaultListModel mdLeft, mdRight;
    private List<Contact> contacts, aContacts, sContacts;
    
    private Session hSession;

    /**
     * Constructor
     */
    public SimpleContactSearch() {
        this(new ArrayList<Contact>());
    }
    
    public SimpleContactSearch(List<Contact> contacts) {
        this.sContacts = contacts;
        
        mdLeft = new DefaultListModel();
        mdRight = new DefaultListModel();
        
        initComponents();
        initListeners();
    }

    /**
     * 
     */
    private void initComponents() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500, 400));
        
        initSearchPanel();
        initListPanel();
        initButtonPanel();
    }
    
    /**
     * 
     */
    private void initSearchPanel() {
        JPanel pSearch = new JPanel(new GridLayout(1, 2, 5, 0));
        pSearch.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        cfCategory = new JComboBox();
        cfCategory.addItem("Contact Group");
        cfCategory.addItem("Contact Person");
        cfCategory.addItem("Search Result");
        cfCategory.setSelectedIndex(1);
        
        sfKeyword = new JXSearchField("Contact or Group Name");
        
        pSearch.add(cfCategory);
        pSearch.add(sfKeyword);
        
        add(pSearch, BorderLayout.NORTH);
    }
    
    /**
     * 
     */
    private void initListPanel() {
        JPanel pList = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        pList.setLayout(layout);
        pList.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.insets = new Insets(0, 0, 0, 2);
        pList.add(new JLabel("Avaliable Contacts"), gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.insets = new Insets(0, 2, 0, 0);
        pList.add(new JLabel("Selected Contacts"), gbc);
       
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(0, 0, 0, 2);
        ldLeft = new JList();
        JScrollPane slLeft = new JScrollPane(ldLeft);
        slLeft.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        
        pList.add(slLeft, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 1;
        JPanel pSelect = new JPanel(new GridLayout(2, 1, 2, 2));
        bSelect = new JButton(">>");
        bDeselect = new JButton("<<");
        pSelect.add(bSelect);
        pSelect.add(bDeselect);
        pList.add(pSelect, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(0, 2, 0, 0);
        ldRight = new JList();
        JScrollPane slRight = new JScrollPane(ldRight);
        slRight.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        pList.add(slRight, gbc);
        
        add(pList, BorderLayout.CENTER);
    }
    
    
    /**
     * 
     */
    private void initButtonPanel() {
        bOK = new JButton(RES_GLOBAL.getString("label.ok"));
        bCancel = new JButton(RES_GLOBAL.getString("label.cancel"));
        bClear = new JButton(RES_GLOBAL.getString("label.clear.value"));
        
        bOK.setPreferredSize(bClear.getPreferredSize());
        bCancel.setPreferredSize(bClear.getPreferredSize());

        //Lay out the buttons from left to right.
        JPanel pButton = new JPanel();
        pButton.setLayout(new BoxLayout(pButton, BoxLayout.LINE_AXIS));
        pButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pButton.add(Box.createHorizontalGlue());
        pButton.add(bOK);
        pButton.add(Box.createRigidArea(new Dimension(5, 0)));
        pButton.add(bCancel);
        pButton.add(Box.createRigidArea(new Dimension(5, 0)));
        pButton.add(bClear);

        add(pButton, BorderLayout.PAGE_END);
    }

    /**
     * 
     */
    private void initListeners() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                loadData();
            }
        });
    }
    
    /**
     * 
     * @return 
     */
    public List<Contact> getUserData() {
        return new ArrayList<Contact>();
    }
    
    /**
     * 
     */
    private void loadData(final String sql) {
        final HWaitDialog dialog = new HwaitDialog();
        dialog.setModal(true);
        dialog.setTitle("Load Data");
        final SwingWorker<Boolean, Void> t1 = new SwingWorker<Boolean, Void> {
            @Override
            protected Boolean doInBackground() {
                try {
                    hSession = HibernateUtil.getSessionFactory().openSession();
                    Query query = null;
                    if(sql == null) {
                        query = hSession.getNamedQuery("Contact.All");
                    } else {
                        query = hSession.createQuery(sql);
                    }
                    contacts = query.list();
                    return true;
                } catch (Exception ex) {
                    
                } finally {
                    hSessoin.close();
                }
                return false;
            }
            
            @Override
            protected void done() {
                try {
                    
                } catch(Exception ex) {
                    
                } finally {
                    dialog.setVisible(false);
                    dialog.dispose();
                }
            }
        }
        t1.execute();
        dialog.setVisible(true);
    }
    
    private void doSearch() {
        String keyword = sfKeyword.getText().trim();
        StringBuilder sql = new StringBuilder("from Contact a where a.deletedState = 0");
        
        if(!keyword.equals("")) {
            sql.append(" and (a.name like ('")
                .append(keyword)
                .append("')");
        } 
        
        if(!sContacts.isEmpty()) {
            sql.append(" and a.id not in (");
            for(int ii=0; ii<sContacts.size(); ii++) {
                Contact contact = sContact.get(ii);
                if(ii == sContact.size()-1) {
                    sql.append("'")
                        .append(contact.getId())
                        .append("'");
                } else {
                    sql.append("'")
                        .append(contact.getId())
                        .append("',");
                }
            }
            sql.append(")");
        }
        
        loadData(sql.toString());
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton) {
            JButton bb = (JButton) e.getSource();
            int ii = 0;
            if(bb == bSelect) {
                int[] fromindex = lLeft.getSelectedIndices();
                Contact[] from = lLeft.getSelectedValues();
                
                // Then, for each item in the array, we add them to
                // the other list.
                for(i = 0; i < from.length; i++) {
                    sContacts.addElement(from[i]);
                }
                
                // Finally, we remove the items from the first list.
                // We must remove from the bottom, otherwise we try to 
                // remove the wrong objects.
                for(i = (fromindex.length-1); i >=0; i--) {
                    aContacts.remove(fromindex[i]);
                }
            } else if(bb == bDeselect) {
                Contact[] to = lRight.getSelectedValues();
                int[] toindex = lRight.getSelectedIndices();
                
                // Then, for each item in the array, we add them to
                // the other list.
                for(i = 0; i < to.length; i++) {
                    aContacts.addElement(to[i]);
                }
                
                // Finally, we remove the items from the first list.
                // We must remove from the bottom, otherwise we try to
                // remove the wrong objects.
                for(i = (toindex.length-1); i >=0; i--){
                    sContacts.remove(toindex[i]);
                }
            }
        }
    }
}