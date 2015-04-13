package com.softhaxi.shortsage.v1.page;

import com.softhaxi.shortsage.v1.dto.ContactGroup;
import com.softhaxi.shortsage.v1.dto.ContactPerson;
import com.softhaxi.shortsage.v1.enums.PropertyChangeField;
import com.softhaxi.shortsage.v1.forms.ContactGroupActionForm;
import com.softhaxi.shortsage.v1.forms.ContactPersonActionForm;
import com.softhaxi.shortsage.v1.forms.GatewayActionForm;
import com.softhaxi.shortsage.v1.util.HibernateUtil;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
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
        implements ActionListener {
    
    private static final ResourceBundle RES_GLOBAL = ResourceBundle.getBundle("global");
    
    /**
     * Tool bar group items
     */
    private JButton bgNew, bgImport, bgExport, bgDelete, bgRefresh;

    /**
     * panel data
     */
    private JList lGroup, lContact;
    private JPanel pData;
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

        pData = new JPanel(new BorderLayout(4, 0));
        pData.setPreferredSize(new Dimension(500, getHeight()));
        add(pData, BorderLayout.WEST);

        initGroupPanel();
        initContactPanel();
        initFormPanel();
    }

    /**
     *
     */
    private void initGroupPanel() {
        JPanel pGroup = new JPanel(new BorderLayout());
        pGroup.setPreferredSize(new Dimension(200, getHeight()));
        pGroup.setBackground(Color.white);

        JToolBar tbGroup = new JToolBar();
        tbGroup.setBorder(new EmptyBorder(2, 2, 2, 2));

        bgNew = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_plus_12.png")));
        tbGroup.add(bgNew);
//        tbGroup.add(new JToolBar.Separator());

        bgImport = new JButton("Import", new ImageIcon(getClass().getClassLoader().getResource("images/ic_upload_12.png")));
//        tbGroup.add(bgImport);

        bgExport = new JButton("Export", new ImageIcon(getClass().getClassLoader().getResource("images/ic_download_12.png")));
//        tbGroup.add(bgExport);
        tbGroup.add(new JToolBar.Separator());

        bgDelete = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_minus_12.png")));
        tbGroup.add(bgDelete);

        tbGroup.add(Box.createHorizontalGlue());
        bgRefresh = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_refresh_12.png")));
        tbGroup.add(bgRefresh);

        pGroup.add(tbGroup, BorderLayout.NORTH);

        mGroup = new DefaultListModel<String>();
        mGroup.addElement("All Group");
        lGroup = new JList(mGroup);
        lGroup.setSelectedIndex(0);
        pGroup.add(new JScrollPane(lGroup), BorderLayout.CENTER);

        pData.add(pGroup, BorderLayout.WEST);
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
        mContact = new DefaultListModel<String>();
        lContact = new JList(mContact);
        pContact.add(new JScrollPane(lContact), BorderLayout.CENTER);

        pData.add(pContact, BorderLayout.CENTER);
    }

    /**
     *
     */
    private void initFormPanel() {
        pForm = new ContactPersonActionForm();

        add(pForm, BorderLayout.CENTER);
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
        bgNew.addActionListener(this);
        bgRefresh.addActionListener(this);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    /**
     *
     */
    private void loadingData() {
        loadGroupData();
        loadGroupData();
    }

    /**
     * 
     */
    private synchronized void loadGroupData() {
        firePropertyChange(PropertyChangeField.LOADING.toString(), false, true);
        SwingWorker<Boolean, Void> t1 = new SwingWorker<Boolean, Void>() {

            @Override
            protected Boolean doInBackground() throws Exception {
                try {
                    hSession = HibernateUtil.getSessionFactory().openSession();
                    hSession.getTransaction().begin();
                    Query query = hSession.createQuery("from ContactGroup");
                    dGroup = query.list();

                    hSession.getTransaction().commit();
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
                    mGroup.removeAllElements();
                    mGroup.addElement("All Group");

                    for (Iterator ii = dGroup.iterator(); ii.hasNext();) {
                        ContactGroup group = (ContactGroup) ii.next();
                        mGroup.addElement(group.getName());

                    }
                    lGroup.setSelectedIndex(0);

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
                        firePropertyChange(PropertyChangeField.LOADING.toString(), true, false);
                    }
                }
            }
        });
        t1.execute();
    }

    /**
     * 
     */
    private synchronized void loadContactData() {
        firePropertyChange(PropertyChangeField.LOADING.toString(), false, true);
        SwingWorker<Boolean, Void> t1 = new SwingWorker<Boolean, Void>() {

            @Override
            protected Boolean doInBackground() throws Exception {
                try {
                    hSession = HibernateUtil.getSessionFactory().openSession();
                    hSession.getTransaction().begin();
                    Query query = hSession.createQuery("from ContactPerson");
                    dContact = query.list();

                    hSession.getTransaction().commit();
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
                    mContact.removeAllElements();
                    for (Iterator jj = dContact.iterator(); jj.hasNext();) {
                        ContactPerson person = (ContactPerson) jj.next();
                        mContact.addElement(person.getName());
                    }
                    if (mContact.size() != 0) {
                        lContact.setSelectedIndex(0);
                    }
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
                        firePropertyChange(PropertyChangeField.LOADING.toString(), true, false);
                    }
                }
            }
        });
        t1.execute();
    }
    // </editor-fold>

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton source = (JButton) e.getSource();
            if (source == bgNew) {
                final JDialog dialog = new JDialog();
                dialog.setModal(true);
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
                dialog.add(form);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            } else if(source == bgRefresh) {
                loadGroupData();
            }
        }
    }
}
