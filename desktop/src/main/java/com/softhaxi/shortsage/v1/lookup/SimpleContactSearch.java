package com.softhaxi.shortsage.v1.lookup;

import com.softhaxi.shortsage.v1.desktop.HWaitDialog;
import com.softhaxi.shortsage.v1.dto.Contact;
import com.softhaxi.shortsage.v1.dto.ContactGroup;
import com.softhaxi.shortsage.v1.dto.ContactPerson;
import com.softhaxi.shortsage.v1.renderer.list.ContactRendererList;
import com.softhaxi.shortsage.v1.util.HibernateUtil;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.swing.SwingWorker;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import org.hibernate.Query;
import org.hibernate.Session;
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
        implements ActionListener, ItemListener {

    private static final ResourceBundle RES_GLOBAL = ResourceBundle.getBundle("global");

    private JComboBox cfCategory;
    private JXSearchField sfKeyword;
    private JList ldLeft, ldRight;
    private JButton bOK, bCancel, bClear,
            bSelect, bDeselect;

    private DefaultListModel mdLeft, mdRight;
    private List<Contact> contacts, aContacts, sContacts;

    private Session hSession;

    private int selection = 1;

    /**
     * Constructor
     */
    public SimpleContactSearch() {
        this(new ArrayList<Contact>());
    }

    public SimpleContactSearch(List<Contact> contacts) {
        this.sContacts = contacts;

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
//        cfCategory.addItem("Search Result");
        cfCategory.setSelectedIndex(1);

        sfKeyword = new JXSearchField("Contact or Group Name");
        sfKeyword.setSearchMode(JXSearchField.SearchMode.REGULAR);
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
        gbc.weightx = 2;
        gbc.weighty = 1;
        gbc.insets = new Insets(0, 0, 0, 2);
        mdLeft = new DefaultListModel<Contact>();
        ldLeft = new JList(mdLeft);
        ldLeft.setCellRenderer(new ContactRendererList());
        JScrollPane slLeft = new JScrollPane(ldLeft);
        slLeft.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        slLeft.setPreferredSize(new Dimension(140, slLeft.getHeight()));

        pList.add(slLeft, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.05;
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
        gbc.weightx = 2;
        gbc.weighty = 1;
        gbc.insets = new Insets(0, 2, 0, 0);
        mdRight = new DefaultListModel<Contact>();
        ldRight = new JList(mdRight);
        ldRight.setCellRenderer(new ContactRendererList());
        JScrollPane slRight = new JScrollPane(ldRight);
        slRight.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        slRight.setPreferredSize(new Dimension(140, slRight.getHeight()));
        pList.add(slRight, gbc);

        add(pList, BorderLayout.CENTER);
    }
    
    private void initListPanel2() {
        JPanel pList = new JPanel();
        pList.setLayout(null);
        pList.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
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
        addAncestorListener(new AncestorListener() {

            @Override
            public void ancestorAdded(AncestorEvent event) {
                doSearch();
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {

            }

            @Override
            public void ancestorMoved(AncestorEvent event) {

            }
        });
        bSelect.addActionListener(this);
        bDeselect.addActionListener(this);
        cfCategory.addItemListener(this);
        sfKeyword.addActionListener(this);
        bOK.addActionListener(this);
        bCancel.addActionListener(this);
        bClear.addActionListener(this);
    }

    /**
     *
     * @return
     */
    public List<Contact> getUserData() {
        return sContacts;
    }
    
    public void setUserData(List<Contact> userData) {
        this.sContacts = userData;
        
        for (Contact contact : sContacts) {
            mdRight.addElement(contact);
        }
    }

    /**
     *
     */
    private void loadData(final String sql) {
        final HWaitDialog dialog = new HWaitDialog("Load Data");
        dialog.setModal(true);
        final SwingWorker<Boolean, Void> t1 = new SwingWorker<Boolean, Void>() {
            @Override
            protected Boolean doInBackground() {
                boolean result = false;
                try {
                    hSession = HibernateUtil.getSessionFactory().openSession();
                    Query query = null;
                    if (sql == null) {
                        query = hSession.getNamedQuery("Contact.All");
                    } else {
                        query = hSession.createQuery(sql);
                    }
                    contacts = query.list();
                    result = true;
                } catch (Exception ex) {
                    Logger.getLogger(SimpleContactSearch.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    hSession.close();
                }
                return result;
            }

            @Override
            protected void done() {
                try {
                    if (!isCancelled()) {
                        mdLeft.removeAllElements();
                        for (Contact contact : contacts) {
                            if (selection == 0) {
                                if (contact instanceof ContactGroup) {
                                    mdLeft.addElement(contact);
                                }
                            } else if (selection == 1) {
                                if (contact instanceof ContactPerson) {
                                    mdLeft.addElement(contact);
                                }
                            } else if (selection == 2) {
                                mdLeft.addElement(contact);
                            }
                        }
                        
                    }
                } catch (Exception ex) {
                    Logger.getLogger(SimpleContactSearch.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    dialog.setVisible(false);
                    dialog.dispose();
                }
            }
        };
        t1.execute();
        dialog.setVisible(true);
    }

    private void doSearch() {
        String keyword = sfKeyword.getText().trim().toUpperCase();
        StringBuilder sql = new StringBuilder("from Contact a where a.deletedState = 0");

        if (!keyword.equals("")) {
            sql.append(" and a.name like ('%")
                    .append(keyword)
                    .append("%')");
        }

        if (!sContacts.isEmpty()) {
            sql.append(" and a.id not in (");
            for (int ii = 0; ii < sContacts.size(); ii++) {
                Contact contact = sContacts.get(ii);
                if (ii == sContacts.size() - 1) {
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
        if (e.getSource() instanceof JButton) {
            JButton bb = (JButton) e.getSource();
            if (bb == bSelect) {
                int[] fromindex = ldLeft.getSelectedIndices();
                List<Contact> slTemp = ldLeft.getSelectedValuesList();

                // Then, for each item in the array, we add them to
                // the other list.
                for (Contact contact : slTemp) {
                    mdRight.addElement(contact);
                    sContacts.add(contact);
                }

                // Finally, we remove the items from the first list.
                // We must remove from the bottom, otherwise we try to 
                // remove the wrong objects.
                for (int i = (fromindex.length - 1); i >= 0; i--) {
                    mdLeft.remove(fromindex[i]);
                }
            } else if (bb == bDeselect) {
                List<Contact> srTemp = ldRight.getSelectedValuesList();
                int[] toindex = ldRight.getSelectedIndices();

                // Then, for each item in the array, we add them to
                // the other list.
                for (Contact contact : srTemp) {
                    if (selection == 0) {
                        if (contact instanceof ContactGroup) {
                            mdLeft.addElement(contact);
                        }
                    } else if (selection == 1) {
                        if (contact instanceof ContactPerson) {
                            mdLeft.addElement(contact);
                        }
                    } else if (selection == 2) {
                        mdLeft.addElement(contact);
                    }
                }

                // Finally, we remove the items from the first list.
                // We must remove from the bottom, otherwise we try to
                // remove the wrong objects.
                for (int i = (toindex.length - 1); i >= 0; i--) {
                    mdRight.remove(toindex[i]);
                    sContacts.remove(toindex[i]);
                }
            } else if (bb == bOK) {
                firePropertyChange("select", false, true);
            } else if (bb == bCancel) {
                firePropertyChange("cancel", false, true);
            } else if (bb == bClear) {
                firePropertyChange("clear", false, true);
            } 
        } else if (e.getSource() instanceof JXSearchField) {
            JXSearchField ss = (JXSearchField) e.getSource();
            if (ss == sfKeyword) {
                if (cfCategory.getItemCount() == 2) {
                    cfCategory.addItem("Search Result");
                } else if(cfCategory.getSelectedIndex() == 2){
                    doSearch();
                }
                cfCategory.setSelectedIndex(2);
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() instanceof JComboBox) {
            JComboBox cc = (JComboBox) e.getSource();
            if (cc == cfCategory) {
                selection = cc.getSelectedIndex();
                if (selection != 2) {
                    sfKeyword.setText("");
                    if (cfCategory.getItemCount() == 3) {
                        cfCategory.removeItemAt(2);
                    }
                }
                doSearch();
            }
        }
    }
}
