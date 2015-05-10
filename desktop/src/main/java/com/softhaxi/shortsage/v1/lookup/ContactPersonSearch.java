package com.softhaxi.shortsage.v1.lookup;

import com.softhaxi.shortsage.v1.desktop.HNumberedTable;
import com.softhaxi.shortsage.v1.desktop.HWaitDialog;
import com.softhaxi.shortsage.v1.dto.ContactPerson;
import com.softhaxi.shortsage.v1.enums.PropertyChangeField;
import com.softhaxi.shortsage.v1.page.DashboardPage;
import com.softhaxi.shortsage.v1.renderer.TableHeaderCenterRender;
import com.softhaxi.shortsage.v1.util.HibernateUtil;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jdesktop.swingx.JXSearchField;
import org.jdesktop.swingx.JXTable;

/**
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class ContactPersonSearch extends JPanel
        implements ActionListener {

    private static final ResourceBundle RES_GLOBAL = ResourceBundle.getBundle("global");

    private final static String[] COLUMN_NAMES = new String[]{
        "Contact Person",
        "Phone Number"
    };

    private JComboBox cfCategory;
    private JXSearchField sfKeyword;
    private JXTable tdData;
    private JButton bOK, bCancel;

    private DefaultTableModel mdData;

    /**
     *
     */
    private List<ContactPerson> data, sContacts;
    private ContactPerson userData;

    private Session hSession;

    /**
     *
     */
    public ContactPersonSearch() {
        this(new ArrayList<ContactPerson>());
    }

    /**
     *
     * @param contacts
     */
    public ContactPersonSearch(List<ContactPerson> contacts) {
        this.sContacts = contacts;

        initComponents();
        initListeners();
    }

    /**
     *
     */
    private void initComponents() {
        setLayout(new BorderLayout(0, 10));
        setPreferredSize(new Dimension(500, 400));
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        initSearchPanel();
        initTablePanel();
        initButtonPanel();
    }

    /**
     *
     */
    private void initSearchPanel() {
        JPanel pSearch = new JPanel(new GridLayout(1, 2, 5, 0));

        cfCategory = new JComboBox();
        cfCategory.addItem("Contact Person");
        cfCategory.setSelectedIndex(0);
        cfCategory.setEnabled(false);

        sfKeyword = new JXSearchField("Contact Name");

        pSearch.add(cfCategory);
        pSearch.add(sfKeyword);

        add(pSearch, BorderLayout.NORTH);
    }

    private void initTablePanel() {
        tdData = new JXTable();
        tdData.setEditable(false);

        mdData = new DefaultTableModel(COLUMN_NAMES, 0);
        tdData.setModel(mdData);
        tdData.setShowGrid(false);
        tdData.setIntercellSpacing(new Dimension(0, 0));
        tdData.getTableHeader().setDefaultRenderer(new TableHeaderCenterRender(tdData));
        tdData.getColumnModel().getColumn(0).setPreferredWidth(200);
        tdData.getColumnModel().getColumn(1).setPreferredWidth(200);

        JScrollPane sPane = new JScrollPane(tdData);
        HNumberedTable rowTable = new HNumberedTable(tdData);
        sPane.setRowHeaderView(rowTable);
        sPane.setCorner(JScrollPane.UPPER_LEFT_CORNER,
                rowTable.getTableHeader());

        add(sPane, BorderLayout.CENTER);
    }

    /**
     *
     */
    private void initButtonPanel() {
        bOK = new JButton(RES_GLOBAL.getString("label.ok"));
        bCancel = new JButton(RES_GLOBAL.getString("label.cancel"));

        bOK.setPreferredSize(bCancel.getPreferredSize());

        //Lay out the buttons from left to right.
        JPanel pButton = new JPanel();
        pButton.setLayout(new BoxLayout(pButton, BoxLayout.LINE_AXIS));
//        pButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pButton.add(Box.createHorizontalGlue());
        pButton.add(bOK);
        pButton.add(Box.createRigidArea(new Dimension(5, 0)));
        pButton.add(bCancel);

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
        bOK.addActionListener(this);
        bCancel.addActionListener(this);
    }

    /**
     *
     * @param keyword
     */
    private void loadData(final String sql) {
        final HWaitDialog dialog = new HWaitDialog("Loading Data");
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
                    data = query.list();

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
                    mdData = new DefaultTableModel(COLUMN_NAMES, 0);
                    Object[] obj = null;
                    for (ContactPerson contact : data) {
                        obj = new Object[2];
                        obj[0] = (contact.getName() == null || contact.getName().equals("")) ? String.format("%s %s %s", contact.getFirstName(),
                                contact.getMidName(), contact.getLastName()) : contact.getName();
                        obj[1] = contact.getPhone();

                        mdData.addRow(obj);
                    }
                    tdData.setModel(mdData);
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
                        dialog.setVisible(false);
                        dialog.dispose();
                        firePropertyChange(PropertyChangeField.LOADING.toString(), true, false);
                    }
                }
            }
        });
        t1.execute();
        dialog.setVisible(true);
    }

    /**
     *
     */
    private void doSearch() {
        String keyword = sfKeyword.getText().trim();
        StringBuilder sql = new StringBuilder("from ContactPerson p where p.deletedState = 0");

        if (!sContacts.isEmpty()) {
            sql.append(" and p.id not in (");

            for (int ii = 0; ii < sContacts.size(); ii++) {
                ContactPerson contact = sContacts.get(ii);
                if (ii != sContacts.size() - 1) {
                    sql.append("'")
                            .append(contact.getId())
                            .append("',");
                } else {
                    sql.append("'")
                            .append(contact.getId())
                            .append("'");
                }
            }
            sql.append(")");
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
        loadData(sql.toString());
    }

    /**
     *
     * @return
     */
    public ContactPerson getUserData() {
        return userData;
    }

    /**
     *
     * @param contacts
     */
    public void setSelectedContacts(List<ContactPerson> contacts) {
        this.sContacts = contacts;
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton bb = (JButton) e.getSource();
            if (bb == bOK) {
                if (tdData.getSelectedRow() != -1) {
                    userData = data.get(tdData.getSelectedRow());
                }
                firePropertyChange("select", false, true);
            } else if (bb == bCancel) {
                firePropertyChange("cancel", false, true);
            }
        }
    }
}
