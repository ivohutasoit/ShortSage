package com.softhaxi.shortsage.v1.page;

import com.softhaxi.shortsage.v1.desktop.HNumberedTable;
import com.softhaxi.shortsage.v1.dto.OutboxMessage;
import com.softhaxi.shortsage.v1.dto.OutboxMessage;
import com.softhaxi.shortsage.v1.enums.PropertyChangeField;
import com.softhaxi.shortsage.v1.forms.MessageActionForm;
import com.softhaxi.shortsage.v1.renderer.TableHeaderCenterRender;
import com.softhaxi.shortsage.v1.util.HibernateUtil;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.SwingWorker;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import net.java.dev.designgridlayout.DesignGridLayout;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jdesktop.swingx.JXSearchField;
import org.jdesktop.swingx.JXTable;
import org.smslib.OutboundMessage;

/**
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class OutboxPage extends JPanel
        implements ActionListener, ItemListener,
        ListSelectionListener {

    private final static ResourceBundle RES_GLOBAL = ResourceBundle.getBundle("global");

    private final static String[] COLUMN_NAME = new String[]{
        "Contact",
        "Message",
        "Date",
        "Status"
    };

    private JPanel pNorth;
    private JPanel pCenter;
    private JPanel pSouth;
    
    /**
     * Tool bar items
     */
    private JButton bNew, bDelete, bRefresh;

    private JXSearchField sfSearch;
    private JComboBox cfViews;
    private JXTable ttData;

    /**
     * Data
     */
    private DefaultTableModel mData;
    private List<OutboxMessage> data;

    /**
     * Database Connection
     */
    private Session hSession;

    /**
     * Main Constructor
     */
    public OutboxPage() {
        setVisible(false);
        initComponents();
        initListeners();
    }

    // <editor-fold defaultstate="collapsed" desc="Region Inititalization">  
    /**
     * Initialize components of the panel
     */
    private void initComponents() {
        setLayout(new BorderLayout());

        initNorthPanel();
        initCenterPanel();
        initSouthPanel();
    }

    /**
     *
     */
    private void initNorthPanel() {
        pNorth = new JPanel();
        sfSearch = new JXSearchField(RES_GLOBAL.getString("label.search.item"));
        sfSearch.setSearchMode(JXSearchField.SearchMode.REGULAR);
        cfViews = new JComboBox();
        cfViews.addItem("All Items");

        DesignGridLayout layout = new DesignGridLayout(pNorth);
        layout.margins(0.5, 0.5, 0.5, 0.5);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.search") + " :"))
                .add(sfSearch)
                .grid(new JLabel(RES_GLOBAL.getString("label.view") + " :"))
                .add(cfViews);

        add(pNorth, BorderLayout.NORTH);
    }

    /**
     *
     */
    private void initCenterPanel() {
        pCenter = new JPanel(new BorderLayout());
        pCenter.setBorder(new EmptyBorder(0, 4, 4, 4));
        JToolBar pToolbar = new JToolBar();
        pToolbar.setFloatable(false);
        pToolbar.setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(2, 2, 2, 2)));

        bNew = new JButton(RES_GLOBAL.getString("label.new"), new ImageIcon(getClass().getClassLoader().getResource("images/ic_new.png")));
        pToolbar.add(bNew);
        //pToolbar.add(new JButton(RES_GLOBAL.getString("label.edit"), new ImageIcon(getClass().getClassLoader().getResource("images/ic_edit.png"))));
        pToolbar.addSeparator();
        bDelete = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_delete.png")));
        pToolbar.add(bDelete);
        pToolbar.add(Box.createHorizontalGlue());
        bRefresh = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_refresh.png")));
        pToolbar.add(bRefresh);

        pCenter.add(pToolbar, BorderLayout.NORTH);

        ttData = new JXTable();
        ttData.setEditable(false);

        mData = new DefaultTableModel(COLUMN_NAME, 0);
        ttData.setModel(mData);
        ttData.setShowGrid(false);
        ttData.setIntercellSpacing(new Dimension(0, 0));
        ttData.getTableHeader().setDefaultRenderer(new TableHeaderCenterRender(ttData));
        ttData.getColumnModel().getColumn(0).setPreferredWidth(100);
        ttData.getColumnModel().getColumn(1).setPreferredWidth(450);
        ttData.getColumnModel().getColumn(2).setPreferredWidth(80);

        JScrollPane sPane = new JScrollPane(ttData);
        HNumberedTable rowTable = new HNumberedTable(ttData);
        sPane.setRowHeaderView(rowTable);
        sPane.setCorner(JScrollPane.UPPER_LEFT_CORNER,
                rowTable.getTableHeader());

        pCenter.add(sPane, BorderLayout.CENTER);

        add(pCenter, BorderLayout.CENTER);
    }

    /**
     *
     */
    private void initSouthPanel() {

    }

    /**
     * Initialize listeners for all components of frame
     */
    private void initListeners() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                loadData();
            }
        });
        sfSearch.addActionListener(this);
        cfViews.addItemListener(this);
        bNew.addActionListener(this);
        bDelete.addActionListener(this);
        bRefresh.addActionListener(this);
        ttData.addMouseListener(new MouseAdapter() {
        });
        ttData.getSelectionModel().addListSelectionListener(this);
    }
    // </editor-fold>   

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    /**
     * 
     */
    private void initTableModel() {
        mData = new DefaultTableModel(COLUMN_NAME, 0);
    Object[] obj = null;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    for (OutboxMessage message : data) {
        obj = new Object[4];
        obj[0] = message.getContact();
        obj[1] = message.getText();
        obj[2] = sdf.format(message.getDate());
        obj[3] = message.getStatus() == 1 ? OutboundMessage.MessageStatuses.UNSENT.toString() :
                message.getStatus() == 2 ? OutboundMessage.MessageStatuses.SENT.toString() :
                OutboundMessage.MessageStatuses.FAILED.toString();

        mData.addRow(obj);
        mData.fireTableDataChanged();
    }
    }
    
    private void loadData() {
        firePropertyChange(PropertyChangeField.LOADING.toString(), false, true);
        SwingWorker<Boolean, Void> t1 = new SwingWorker<Boolean, Void>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                try {
                    hSession = HibernateUtil.getSessionFactory().openSession();
                    hSession.getTransaction().begin();
                    Query query = hSession.getNamedQuery("Outbox.All");
                    dMessage = query.list();

                    hSession.getTransaction().commit();
                    hSession.close();
                    return true;
                } catch (Exception ex) {
                    Logger.getLogger(DashboardPage.class.getName()).log(Level.SEVERE, null, ex);
                }
                return false;
            }

            @Override
            protected void done () {
                if (!isCancelled()) {
                    initTableModel();
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
     * @param message 
     */
    private void deleteData(final OutboxMessage message) {
        firePropertyChange(PropertyChangeField.DELETING.toString(), false, true);
        final SwingWorker<Boolean, Void> t1 = new SwingWorker<Boolean, Void>() {
           @Override
           protected Boolean doInBackground() {
                try {
                    hSession = HibernateUtil.getSessionFactory().openSession();
                    hSession.getTransaction().begin();
                    hSession.delete(message);
                    hSession.getTransaction().commit();
                    hSession.close();
                    return true;
                } catch (Exception ex) {
                    Logger.getLogger(OutboxPage.class.getName()).log(Level.SEVERE, null, ex);
                }
                return false;
           }
        };
        
        t1.addPropertyChangeListener(new PropertyChangeListener() {
           @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("state".equals(evt.getPropertyName())
                 && SwingWorker.StateValue.DONE == evt.getNewValue()) {
                    try {
                        if(t1.get() == true) {
                            firePropertyChange(PropertyChangeField.DELETING.toString(), true, false);
                            loadData();
                        }
                    } catch (InterruptedException | ExecutionException ex) {
                        Logger.getLogger(OutboxPage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 }
            }
        });
        t1.execute();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="ActionListener Implementation">
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton bb = (JButton) e.getSource();
            if (bb == bRefresh) {
                loadMessageData();
            } else if (bb == bNew) {
                final JDialog dialog = new JDialog();
                dialog.setModal(true);
                dialog.setTitle(RES_GLOBAL.getString("label.new") + " Message");
                MessageActionForm form = new MessageActionForm();
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
                        loadData();
                    }
                });
                dialog.add(form);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            } else if (bb == bDelete) {
                if(ttData.getSelectedRow() == -1) {
                   JOptionPane.showMessageDialog(null, "No data selected to be deleted.", "Inbox Message", JOptionPane.WARNING_MESSAGE);
                   return;
                }
                
                InboxMessage message = data.get(ttData.getSelectedRow());
                
                int result = JOptionPane.showConfirmDialog(null, "Delete Message from " + message.getContact() + "?", 
                        "Inbox Message", JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.YES_OPTION) {
                  // running delete 
                    deleteData(message);
                }
            } else if(bb == bRefresh) {
                loadData();
            }
        } else if (e.getSource() instanceof JXSearchField) {
            JOptionPane.showMessageDialog(null, "Search Implementation");
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="ItemListener Implementation">
    @Override
    public void itemStateChanged(ItemEvent e) {
        int state = e.getStateChange();
        System.out.println((state == e.SELECTED) ? "Selected" : "Deselected");
        System.out.println("Item: " + e.getItem());
        ItemSelectable is = e.getItemSelectable();
    }
      // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="ListSelectionListener Implementation"> 
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (ttData.getSelectedRow() > -1) {
            // print first column value from selected row
            System.out.println(ttData.getValueAt(ttData.getSelectedRow(), 0).toString());
        }
    }
    // </editor-fold>
}
