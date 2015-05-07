package com.softhaxi.shortsage.v1.page;

import com.softhaxi.shortsage.v1.dto.ContactGroup;
import com.softhaxi.shortsage.v1.dto.ContactPerson;
import com.softhaxi.shortsage.v1.enums.ActionState;
import com.softhaxi.shortsage.v1.enums.PropertyChangeField;
import com.softhaxi.shortsage.v1.forms.ContactGroupActionForm;
import com.softhaxi.shortsage.v1.forms.ContactPersonActionForm;
import com.softhaxi.shortsage.v1.renderer.ContactPersonRenderer;
import com.softhaxi.shortsage.v1.util.HibernateUtil;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jdesktop.swingx.JXSearchField;

/**
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class PhoneBookPage extends JPanel
        implements ActionListener, ListSelectionListener {

    private static final ResourceBundle RES_GLOBAL = ResourceBundle.getBundle("global");

    /**
     * Tool bar group items
     */
    private JButton bNewGroup, bImportGroup,
            bExportGroup, bDeleteGroup, bRefreshGroup;

    /**
     * panel data
     */
    private JList lGroup, lContact;
    private JPanel pData, pList;
    private ContactPersonActionForm pForm;

    /**
     *
     */
    private JXSearchField sfContact;

    /**
     * Data
     */
    private DefaultListModel mGroup, mContact;
    private List dGroup, dContact;

    /**
     * Database connection
     */
    private Session hSession;

    /**
     *
     */
    public PhoneBookPage() {
        setVisible(false);
        initComponents();
        initListeners();
    }

    // <editor-fold defaultstate="collapsed" desc="Region Initialization">
    /**
     *
     */
    private void initComponents() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(2, 2, 2, 2));

        pData = new JPanel(new GridLayout(1, 2, 4, 0));
        add(pData, BorderLayout.CENTER);

        pList = new JPanel(new GridLayout(1, 2, 4, 0));
        pData.add(pList);

        initGroupPanel();
        initContactPanel();
        initFormPanel();
    }

    /**
     *
     */
    private void initGroupPanel() {
        JPanel pGroup = new JPanel(new BorderLayout());
        pGroup.setBackground(Color.white);

        JToolBar tbGroup = new JToolBar();
        tbGroup.setBorder(new EmptyBorder(2, 2, 2, 2));

        bNewGroup = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_plus_12.png")));
        tbGroup.add(bNewGroup);
//        tbGroup.add(new JToolBar.Separator());

        bImportGroup = new JButton("Import", new ImageIcon(getClass().getClassLoader().getResource("images/ic_upload_12.png")));
//        tbGroup.add(bImportGroup);

        bExportGroup = new JButton("Export", new ImageIcon(getClass().getClassLoader().getResource("images/ic_download_12.png")));
//        tbGroup.add(bExportGroup);
        tbGroup.add(new JToolBar.Separator());

        bDeleteGroup = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_minus_12.png")));
        tbGroup.add(bDeleteGroup);

        tbGroup.add(Box.createHorizontalGlue());
        bRefreshGroup = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_refresh_12.png")));
        tbGroup.add(bRefreshGroup);

        pGroup.add(tbGroup, BorderLayout.NORTH);

        mGroup = new DefaultListModel<String>();
        mGroup.addElement("ALL");
        lGroup = new JList(mGroup);
        lGroup.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lGroup.setSelectedIndex(0);
        pGroup.add(new JScrollPane(lGroup), BorderLayout.CENTER);

        pList.add(pGroup);
    }

    /**
     *
     */
    private void initContactPanel() {
        JPanel pContact = new JPanel(new BorderLayout(0, 4));
        pContact.setBorder(new EmptyBorder(2, 2, 2, 2));
        pContact.setBackground(Color.white);
        sfContact = new JXSearchField("Search contact person...");
        sfContact.setSearchMode(JXSearchField.SearchMode.REGULAR);
        sfContact.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Under Construction");
            }
        });

        pContact.add(sfContact, BorderLayout.NORTH);
        mContact = new DefaultListModel<ContactPerson>();
        lContact = new JList(mContact);
        lContact.setCellRenderer(new ContactPersonRenderer());
        pContact.add(new JScrollPane(lContact), BorderLayout.CENTER);

        pList.add(pContact);
    }

    /**
     *
     */
    private void initFormPanel() {
        pForm = new ContactPersonActionForm();

        pData.add(pForm);
    }

    /**
     *
     */
    private void initListeners() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                loadingData();
            }
        });
        addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent e) {
            }

            @Override
            public void ancestorMoved(AncestorEvent e) {
            }

            @Override
            public void ancestorRemoved(AncestorEvent e) {
                // do action after removing from parent
            }
        });
        bNewGroup.addActionListener(this);
        bRefreshGroup.addActionListener(this);
        lGroup.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                //System.out.println(evt.getButton());
                if (evt.getButton() == 1) {
                    if (evt.getClickCount() == 2) {
                        // Double-click detected
                        int index = list.locationToIndex(evt.getPoint());

                        if (index == 0) {
                            return;
                        }

                        final JDialog dialog = new JDialog();
                        dialog.setModal(true);
                        dialog.setTitle(RES_GLOBAL.getString("label.new") + " Group");
                        ContactGroupActionForm form = new ContactGroupActionForm((ContactGroup) dGroup.get(index - 1));
                        form.addPropertyChangeListener(new PropertyChangeListener() {
                            /**
                             *
                             * @param evt
                             */
                            @Override
                            public void propertyChange(PropertyChangeEvent evt) {
                                firePropertyChange(evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());

                                if (evt.getPropertyName().equals(PropertyChangeField.SAVING.toString())) {
                                    boolean value = (boolean) evt.getNewValue();
                                    if (value == false) {
                                        dialog.dispose();
                                    }
                                }
                            }
                        });
                        dialog.add(form);
                        dialog.pack();
                        dialog.setLocationRelativeTo(null);
                        dialog.setVisible(true);
                    }
//                else if (evt.getClickCount() == 3) {
//                    // Triple-click detected
//                    int index = list.locationToIndex(evt.getPoint());
//                }
                }
            }
        });
        lGroup.getSelectionModel().addListSelectionListener(this);

        lContact.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                //System.out.println(evt.getButton());
                if (evt.getButton() == 1) {
                    int index = list.locationToIndex(evt.getPoint());

                    if (index == 0) {
                        return;
                    }
                    ContactPerson person = (ContactPerson) dContact.get(index);
                    pForm.setObject(person);
                    if (evt.getClickCount() == 1) {
                        pForm.setActionState(ActionState.READ);
                    } else if (evt.getClickCount() == 2) {
                        pForm.setActionState(ActionState.EDIT);
                    }
                }
            }
        });
        lContact.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                ListSelectionModel lsm = (ListSelectionModel) e.getSource();

                int firstIndex = e.getFirstIndex();
                int lastIndex = e.getLastIndex();
                boolean isAdjusting = e.getValueIsAdjusting();

                System.out.println("Event for indexes "
                        + firstIndex + " - " + lastIndex
                        + "; isAdjusting is " + isAdjusting
                        + "; selected indexes:");

                if (lsm.isSelectionEmpty()) {
                    System.out.println(" <none>");
                } else {
                    // Find out which indexes are selected.
                    int minIndex = lsm.getMinSelectionIndex();
                    int maxIndex = lsm.getMaxSelectionIndex();
                    for (int i = minIndex; i <= maxIndex; i++) {
                        if (lsm.isSelectedIndex(i)) {
                            ContactPerson person = (ContactPerson) dContact.get(i);
                            pForm.setObject(person);
                        }
                    }
                }
                System.out.println();
            }
        });
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    /**
     *
     */
    private void loadingData() {
        doGroupSearch();
    }

    /**
     *
     */
    private void doGroupSearch() {
        loadGroupData(null);
    }

    /**
     *
     */
    private void doContactSearch() {
        String keyword = sfContact.getText().trim();
        StringBuilder sql = new StringBuilder();

        ContactGroup group = null;
        if (lGroup.getSelectedIndex() != 0) {
            sql.append("select p from ContactGroupLine a")
                    .append(" join a.person p where a.deletedState = 0 and p.deletedState = 0");

            group = (ContactGroup) mGroup.get(lGroup.getSelectedIndex());
            sql.append(" and a.group = '")
                    .append(group.getId())
                    .append("' ");
        } else {
            sql.append("from ContactPerson p where p.deletedState = 0");
        }

        if (!keyword.equals("")) {
            if (!keyword.contains("*")) {
                sql.append(" and (p.name = '")
                        .append(keyword)
                        .append("' or p.id = '")
                        .append(keyword)
                        .append("')");
            } else {
                sql.append(" and (p.name like '")
                        .append(keyword)
                        .append("' or p.id like '")
                        .append("')");
            }
        }
        System.out.println(sql.toString());
        loadContactData(sql.toString());
    }

    /**
     *
     */
    private void loadGroupData(final String sql) {
        firePropertyChange(PropertyChangeField.LOADING.toString(), false, true);
        SwingWorker<Boolean, Void> t1 = new SwingWorker<Boolean, Void>() {

            @Override
            protected Boolean doInBackground() throws Exception {
                try {
                    hSession = HibernateUtil.getSessionFactory().openSession();
                    Query query = null;
                    if (sql == null) {
                        query = hSession.getNamedQuery("ContactGroup.All");
                    } else {
                        query = hSession.createQuery(sql);
                    }
                    dGroup = query.list();
                    hSession.close();
                    return true;
                } catch (Exception ex) {
                    Logger.getLogger(DashboardPage.class.getName()).log(Level.SEVERE, null, ex);
                }
                return false;
            }

            @Override
            protected void done() {
                if (!isCancelled()) {
                    mGroup = new DefaultListModel<ContactGroup>();
                    ContactGroup gAll = new ContactGroup();
                    gAll.setName("All");
                    mGroup.addElement(gAll);

                    for (Iterator ii = dGroup.iterator(); ii.hasNext();) {
                        mGroup.addElement((ContactGroup) ii.next());
                    }
                    lGroup.setModel(mGroup);
                }
                firePropertyChange(PropertyChangeField.LOADING.toString(), true, false);
            }
        };
        t1.addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals(PropertyChangeField.LOADING.toString())) {
                    boolean value = (boolean) evt.getNewValue();
                    if (value == false) {
                        lGroup.setSelectedIndex(0);
                    }
                    doContactSearch();
                }
            }
        });
        t1.execute();
    }

    /**
     *
     */
    private void loadContactData(final String sql) {
        firePropertyChange(PropertyChangeField.LOADING.toString(), false, true);
        SwingWorker<Boolean, Void> t1 = new SwingWorker<Boolean, Void>() {

            @Override
            protected Boolean doInBackground() throws Exception {
                try {
                    hSession = HibernateUtil.getSessionFactory().openSession();
                    Query query = null;
                    if (sql == null) {
                        query = hSession.getNamedQuery("ContactPerson.All");
                    } else {
                        query = hSession.createQuery(sql);
                    }
                    dContact = query.list();

                    hSession.close();
                    return true;
                } catch (Exception ex) {
                    Logger.getLogger(DashboardPage.class.getName()).log(Level.SEVERE, null, ex);
                }
                return false;
            }

            @Override
            protected void done() {
                if (!isCancelled()) {
                    mContact = new DefaultListModel<ContactPerson>();
                    for (Iterator jj = dContact.iterator(); jj.hasNext();) {
                        mContact.addElement((ContactPerson) jj.next());
                    }
                    lContact.setModel(mContact);
                }
                firePropertyChange(PropertyChangeField.LOADING.toString(), true, false);
            }
        };
        t1.addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals(PropertyChangeField.LOADING.toString())) {
                    boolean value = (boolean) evt.getNewValue();
                    if (value == false) {
                        if (!mContact.isEmpty()) {
                            lContact.setSelectedIndex(0);
                        }
                        firePropertyChange(PropertyChangeField.LOADING.toString(), true, false);
                    }
                }
            }
        });
        t1.execute();
    }
    // </editor-fold>

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton source = (JButton) e.getSource();
            if (source == bNewGroup) {
                final JDialog dialog = new JDialog();
                dialog.setModal(true);
                dialog.setResizable(false);
                dialog.setTitle(RES_GLOBAL.getString("label.new") + " Group");
                ContactGroupActionForm form = new ContactGroupActionForm();
                form.addPropertyChangeListener(new PropertyChangeListener() {
                    /**
                     *
                     * @param evt
                     */
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        firePropertyChange(evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());

                        if (evt.getPropertyName().equals(PropertyChangeField.SAVING.toString())) {
                            boolean value = (boolean) evt.getNewValue();
                            if (value == false) {
                                dialog.dispose();
                            }
                        }
                    }
                });
                dialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadGroupData(null);
                    }
                });
                dialog.add(form);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            } else if (source == bRefreshGroup) {
                loadGroupData(null);
            }
        } else if (e.getSource() instanceof JXSearchField) {
            JXSearchField ss = (JXSearchField) e.getSource();
            if (ss == sfContact) {
                if (sfContact.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null,
                            "Keyword search cannot be null",
                            "Contact Person - Search Keyword", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                doContactSearch();
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() instanceof JList) {
            ListSelectionModel ll = (ListSelectionModel) e.getSource();
            if (ll == mGroup) {
                doContactSearch();
            } else if (ll == lContact) {

            }
        }

    }
}
