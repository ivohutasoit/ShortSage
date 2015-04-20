package com.softhaxi.shortsage.v1.page;

import com.softhaxi.shortsage.v1.desktop.HNumberedTable;
import com.softhaxi.shortsage.v1.enums.PropertyChangeField;
import com.softhaxi.shortsage.v1.forms.GatewayActionForm;
import com.softhaxi.shortsage.v1.forms.MessageTemplateActionForm;
import com.softhaxi.shortsage.v1.renderer.TableHeaderCenterRender;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ResourceBundle;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import net.java.dev.designgridlayout.DesignGridLayout;
import org.jdesktop.swingx.JXSearchField;
import org.jdesktop.swingx.JXTable;

/**
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class MessageTemplatePage extends JPanel 
        implements ActionListener, ItemListener {
    
    private final static ResourceBundle RES_GLOBAL = ResourceBundle.getBundle("global");

    private final static String[] COLUMN_NAME = new String[]{
        "Name",
        "Message Text",
        "Created At",
        "Created By"
    };

    private JPanel pNorth;
    private JPanel pCenter;
    private JPanel pSouth;

    private JXSearchField tfSearch;
    private JComboBox cfViews;
    private JXTable ttData;
    
    /**
     * Tool bar items
     */
    private JButton bNew, bDelete, bRefresh;
    
    private DefaultTableModel mData;
    
    private Session hSession;
    private List data;
    
    /**
     * Main Constructor
     */
    public MessageTemplatePage() {
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
        
        bNew = new JButton(RES_GLOBAL.getString("label.new"), new ImageIcon(getClass().getClassLoader().getResource("images/ic_new.png")));
        bDelete = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_delete.png")));
        bRefresh = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_refresh.png")));
        
        JToolBar pToolbar = new JToolBar();
        pToolbar.setFloatable(false);
        pToolbar.setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(2, 2, 2, 2)));

        
        
        pToolbar.add(bNew);
        //pToolbar.add(new JButton(RES_GLOBAL.getString("label.edit"), new ImageIcon(getClass().getClassLoader().getResource("images/ic_edit.png"))));
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
        bNew.addActionListener(this);
        bDelete.addActionListener(this);
        bRefresh.addActionListener(this);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                loadData();
            }
        });
        sfSearch.addActionListener(this);
        cfViews.addItemListener(this);
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
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    private void loadData() {
        firePropertyChange(PropertyChangeField.LOADING.toString(), false, true);
        final SwingWorker<Boolean, Void> t1 = new SwingWorker<Boolean, Void> {
           @Override
           protected Boolean doInBackground() {
                try {
                    hSession = HibernateUtil.getSessionFactory().openSession();
                    hSession.getTransaction().begin();
                    Query query = hSession.getNamedQuery("MessageTemplate.All");
                    data = query.list();
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
                    mData = new DefaultTableModel(COLUMN_NAME, 0);
                    Object[] obj = null;
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    for (MessageTemplate template : data) {
                        obj = new Object[4];
                        obj[0] = template.getName();
                        obj[1] = template.getText();
                        obj[2] = sdf.format(template.getCreatedDate());
                        obj[3] = template.getCreatedBy();

                        mData.addRow(obj);
                        mData.fireTableDataChanged();
                    }
                    ttData.setModel(mData);
                }
            }
        }
        
        t1.addPropertyChangeListener(new PropertyChangeListener() {
           @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("state".equals(event.getPropertyName())
                 && SwingWorker.StateValue.DONE == event.getNewValue()) {
                     if(t1.get() == true) {
                        firePropertyChange(PropertyChangeField.LOADING.toString(), true, false);   
                     }
                 }
            }
        });
        t1.execute();
    }
    // </editor-fold>   

    // <editor-fold defaultstate="collapsed" desc="ActionListener Implementation">
    /**
     * 
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton) {
            JButton source = (JButton) e.getSource();
            if(source == bNew) {
                final JDialog dialog = new JDialog();
                dialog.setModal(true);
                dialog.setTitle(RES_GLOBAL.getString("label.new") + " Message Template");
                MessageTemplateActionForm form = new MessageTemplateActionForm();
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
            } else if(source == bDelete) {
                if(ttData.getSelectedRow() == -1) {
                   JOptionPane.showMessageDialog(null, "No data selected to be deleted.", "Message Template", JOptionPane.WARNING_MESSAGE);
                   return;
                }
                
                MessageTemplate template = data.get(ttData.getSelectedRow());
                
                int result = JOptionPane.showConfirmDialog(null, "Delete Message Template " + template.getName() + "?", 
                        "Gateway", JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.YES_OPTION) {
                  // running delete 
                }
            } else if(source == bRefresh) {
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
        System.out.println((state == ItemEvent.SELECTED) ? "Selected" : "Deselected");
        System.out.println("Item: " + e.getItem());
//        ItemSelectable is = e.getItemSelectable();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="ListSelectionListener Implementation"> 
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (ttData.getSelectedRow() > -1) {
            ListSelectionModel lsm = (ListSelectionModel) e.getSource();
            
            InboxMessage message = null;

            if (lsm.isSelectionEmpty()) {
                System.out.println(" <none>");
            } else {
                // Find out which indexes are selected.
                int minIndex = lsm.getMinSelectionIndex();
                int maxIndex = lsm.getMaxSelectionIndex();
                for (int i = minIndex; i <= maxIndex; i++) {
                    if (lsm.isSelectedIndex(i)) {
                        System.out.println(" " + i);
                        message = dMessage.get(i);
                        
                        System.out.println(message.getId());
                    }
                }
            }
            System.out.println();
        }
    }
    // </editor-fold>
}
