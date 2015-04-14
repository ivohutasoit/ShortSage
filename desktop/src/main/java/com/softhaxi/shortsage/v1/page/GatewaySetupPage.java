package com.softhaxi.shortsage.v1.page;

import com.softhaxi.shortsage.v1.desktop.HNumberedTable;
import com.softhaxi.shortsage.v1.dto.Gateway;
import com.softhaxi.shortsage.v1.enums.PropertyChangeField;
import com.softhaxi.shortsage.v1.forms.GatewayActionForm;
import com.softhaxi.shortsage.v1.renderer.TableHeaderCenterRender;
import com.softhaxi.shortsage.v1.util.HibernateUtil;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.table.DefaultTableModel;
import net.java.dev.designgridlayout.DesignGridLayout;
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
public class GatewaySetupPage extends JPanel
        implements ActionListener {

    private final static ResourceBundle RES_GLOBAL = ResourceBundle.getBundle("global");
    private final static String[] COLUMN_NAME = new String[]{
        "Gateway Name",
        "Port",
        "Manufacture",
        "Model",
        "Provider",
        "Status",
        "Created Date"
    };

    private JPanel pNorth;
    private JPanel pCenter;
    private JPanel pSouth;

    /**
     * Tool bar Items
     */
    private JButton bNew, bEdit, bDelete, bRefresh;

    private JXSearchField tfSearch;
    private JComboBox cfViews;
    private JXTable ttData;

    private DefaultTableModel mData;
    private List<Gateway> gData;
    private Session hSession;

    /**
     * Main Constructor
     */
    public GatewaySetupPage() {
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
        tfSearch = new JXSearchField(RES_GLOBAL.getString("label.search.item"));
        tfSearch.setSearchMode(JXSearchField.SearchMode.REGULAR);
        cfViews = new JComboBox();
        cfViews.addItem("All Items");

        DesignGridLayout layout = new DesignGridLayout(pNorth);
        layout.margins(0.5, 0.5, 0.5, 0.5);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.search") + " :"))
                .add(tfSearch)
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

        bNew = new JButton(RES_GLOBAL.getString("label.new"),
                new ImageIcon(getClass().getClassLoader().getResource("images/ic_new.png")));
        bEdit = new JButton(RES_GLOBAL.getString("label.edit"),
                new ImageIcon(getClass().getClassLoader().getResource("images/ic_edit.png")));
        bDelete = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_delete.png")));
        bRefresh = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_refresh.png")));

        pToolbar.add(bNew);
        pToolbar.add(bEdit);
        pToolbar.addSeparator();
        pToolbar.add(bDelete);
        pToolbar.add(Box.createHorizontalGlue());
        pToolbar.add(bRefresh);

        pCenter.add(pToolbar, BorderLayout.NORTH);

        ttData = new JXTable();
        ttData.setEditable(false);

        mData = new DefaultTableModel(COLUMN_NAME, 0);
        ttData.setModel(mData);
        ttData.setShowGrid(false);
        ttData.setIntercellSpacing(new Dimension(0, 0));
        ttData.getTableHeader().setDefaultRenderer(new TableHeaderCenterRender(ttData));
        ttData.getColumnModel().getColumn(0).setPreferredWidth(50);
        ttData.getColumnModel().getColumn(1).setPreferredWidth(20);
        ttData.getColumnModel().getColumn(2).setPreferredWidth(80);
        ttData.getColumnModel().getColumn(3).setPreferredWidth(80);
        ttData.getColumnModel().getColumn(4).setPreferredWidth(80);
        ttData.getColumnModel().getColumn(5).setPreferredWidth(30);

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
        bNew.addActionListener(this);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                firePropertyChange(PropertyChangeField.LOADING.toString(), false, true);
                LoadDataTask t1 = new LoadDataTask();
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
        });
        ttData.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JXTable target = (JXTable) e.getSource();
                    int row = target.getSelectedRow();
                    JOptionPane.showMessageDialog(null, "Clicked row" + row);
                }
            }
        });
    }
    // </editor-fold>   

    // <editor-fold defaultstate="collapsed" desc="ActionListener Implementation">
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton source = (JButton) e.getSource();
            if (source == bNew) {
                final JDialog dialog = new JDialog();
                dialog.setModal(true);
                dialog.setTitle(RES_GLOBAL.getString("label.new") + " Gateway");
                GatewayActionForm form = new GatewayActionForm();
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
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Task Classes">  
    class LoadDataTask extends SwingWorker<Boolean, Void> {

        @Override
        protected Boolean doInBackground() throws Exception {
            try {
                hSession = HibernateUtil.getSessionFactory().openSession();
                hSession.getTransaction().begin();
                Query query = hSession.createQuery("from Gateway");
                gData = query.list();
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
                Object[] obj = null;
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                for (Gateway gateway : gData) {
                    obj = new Object[7];
                    obj[0] = gateway.getName();
                    obj[1] = gateway.getPort();
                    obj[2] = gateway.getManufacture();
                    obj[3] = gateway.getModel();
                    obj[4] = gateway.getProvider();
                    obj[5] = gateway.getStatus() == 1 ? "Active" : "Inactive";
                    obj[6] = sdf.format(gateway.getCreatedOn());

                    mData.addRow(obj);
                    mData.fireTableDataChanged();
                }
            }
            firePropertyChange(PropertyChangeField.LOADING.toString(), true, false);
        }
    }
    // </editor-fold>
}
