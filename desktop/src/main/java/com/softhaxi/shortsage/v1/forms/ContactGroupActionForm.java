package com.softhaxi.shortsage.v1.forms;

import com.softhaxi.shortsage.v1.enums.ActionState;
import com.softhaxi.shortsage.v1.dto.ContactGroup;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import net.java.dev.designgridlayout.DesignGridLayout;
import net.java.dev.designgridlayout.LabelAlignment;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTextField;

public class ContactGroupActionForm extends JPanel
        implements ActionListener {
    
    private static final ResourceBundle RES_GLOBAL = ResourceBundle.getBundle("global");
    
    private final static String[] COLUMN_NAMES = new String[]{
        "Contact Person",
        "Phone Number"
    };
    
    private ContactGroup object;
    private ActionState state;
    
    /**
     * Tool bar items
     */
    private JButton bNew, bEdit, bDelete;
    private JButton bSave, bSaveNew, bCancel;

    /**
     * Fields
     */
    private JXTextField tfName;
    private JTextArea tfRemark;
    private JComboBox cfStatus, cfHandler;

    /**
     * Group Line
     */
    private JXTable ttLine;
    private JButton bNewLine bDeleteLine, bExportLine, bImportLine, bRefreshLine;
    
    /**
     * Data
     */
    private Session hSession;
    private DefaultTableModel mLine;
    private List<ContactGroupLine> dLine;

    /**
     * 
     */
    public ContactGroupActionForm() {
        this(null, ActionState.CREATE);
    }

    /**
     * 
     * @param object 
     */
    public ContactGroupActionForm(ContactGroup object) {
        this(object, ActionState.SHOW);
    }

    /**
     * 
     * @param object
     * @param state 
     */
    public ContactGroupActionForm(ContactGroup object, ActionState state) {
        this.object = object;
        this.state = state;
        
        initComponents();
        initListeners();
        initState();
        initData();
    }

    /**
     * 
     */
    private void initComponents() {
        setLayout(new BorderLayout());
        if (state == ActionState.CREATE) {
            setPreferredSize(new Dimension(450, 250));
        } else {
            setPreferredSize(new Dimension(450, 400));
            initLinePanel();
        }
        initToolbar();
        initFormPanel();
    }
    
    /**
     * 
     */
    private void initToolbar() {
        JToolBar pToolbar = new JToolBar();
        pToolbar.setFloatable(false);

        bNew = new JButton(RES_GLOBAL.getString("label.new"),
                new ImageIcon(getClass().getClassLoader().getResource("images/ic_new.png")));
        pToolbar.add(bNew);

        bEdit = new JButton(RES_GLOBAL.getString("label.edit"),
                new ImageIcon(getClass().getClassLoader().getResource("images/ic_edit.png")));
        pToolbar.add(bEdit);

        bSave = new JButton(RES_GLOBAL.getString("label.save"),
                new ImageIcon(getClass().getClassLoader().getResource("images/ic_save.png")));
        pToolbar.add(bSave);

        bSaveNew = new JButton(RES_GLOBAL.getString("label.save.new"),
                new ImageIcon(getClass().getClassLoader().getResource("images/ic_save_as.png")));
        pToolbar.add(bSaveNew);
        pToolbar.addSeparator();

        bDelete = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_delete.png")));
        pToolbar.add(bDelete);

        bCancel = new JButton(RES_GLOBAL.getString("label.cancel"),
                new ImageIcon(getClass().getClassLoader().getResource("images/ic_cancel.png")));
        pToolbar.add(bCancel);

        add(pToolbar, BorderLayout.NORTH);
    }
    
    /**
     * 
     */
    private void initFormPanel() {
        JPanel pForm = new JPanel();
        
        tfName = new JXTextField(RES_GLOBAL.getString("label.contact.group"));
        tfRemark = new JTextArea();
        tfRemark.setLineWrap(true);
        tfRemark.setRows(3);

        cfStatus = new JComboBox();
        cfStatus.setEnabled(false);
        cfHandler = new JComboBox();

        DesignGridLayout layout = new DesignGridLayout(pForm);
        layout.labelAlignment(LabelAlignment.RIGHT);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.group.name"))).add(tfName).empty();
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.group.desc"))).add(new JScrollPane(tfRemark));
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.status"))).add(cfStatus).empty(2);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.handler"))).add(cfHandler).empty(2);
        
        add(pForm, BorderLayout.CENTER);
    }
    
    private void initLinePanel() {
        JPanel pTable = new JPanel(new BorderLayout());
        JToolBar tbLine = new JToolBar();
        tbLine.setFloatable(false);
        tbLine.setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(2, 2, 2, 2)));
        
        bNewLine = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_plus_12.png")));
        bNewLine.addActionListener(this);
        tbLine.add(bNewLine);
        tbLine.add(new JToolBar.Separator());

        bImportLine = new JButton("Import", new ImageIcon(getClass().getClassLoader().getResource("images/ic_upload_12.png")));
        //tbLine.add(bImportLine);

        bExportLine = new JButton("Export", new ImageIcon(getClass().getClassLoader().getResource("images/ic_download_12.png")));
        //tbLine.add(bExportLine);
        tbLine.add(new JToolBar.Separator());

        bDeleteLine = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_minus_12.png")));
        tbLine.add(bDeleteLine);
        
        tbLine.add(Box.createHorizontalGlue());
        bRefreshLine = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_refresh_12.png")));
        tbLine.add(bRefreshLine);

        pTable.add(tbLine, BorderLayout.NORTH);

        ttLine = new JXTable();
        ttLine.setEditable(false);
        
        mLine = new DefaultTableModel(COLUMN_NAMES, 0);
        ttLine.setModel(mLine);
        ttLine.setShowGrid(false);
        ttLine.setIntercellSpacing(new Dimension(0, 0));
        ttLine.getTableHeader().setDefaultRenderer(new TableHeaderCenterRender(ttData));
        ttLine.getColumnModel().getColumn(0).setPreferredWidth(200);
        ttLine.getColumnModel().getColumn(1).setPreferredWidth(200);

        JScrollPane sPane = new JScrollPane(ttLine);
        HNumberedTable rowTable = new HNumberedTable(ttLine);
        sPane.setRowHeaderView(rowTable);
        sPane.setCorner(JScrollPane.UPPER_LEFT_CORNER,
                rowTable.getTableHeader());

        pTable.add(sPane, BorderLayout.CENTER);
        
        add(pTable, BorderLayout.SOUTH);
    }
    
    private void initListeners() {
        bSave.addActionListener(this);
        bCancel.addActionListener(this);
    }
 
    private void initState() {
        cfStatus.removeAllItems();
        cfHandler.removeAllItems();
        
        if (state == ActionState.CREATE ||
                state == ActionState.UPDATE) {
            
            if(state == ActionState.CREATE) {
                cfStatus.addItem("Create");
                cfHandler.addItem("Created");
                cfHandler.setEnabled(false);
            } else {
                cfStatus.addItem("Actived");
                cfStatus.addItem("Inactived");
                
                cfHandler.addItem("No Action");
                cfHandler.addItem("Activate");
                cfHandler.addItem("Deactivate");
                cfHandler.addItem("Delete");
                
                cfHandler.setEnabled(true);
                tfName.setEnabled(false);
            }
            tfRemark.setEnabled(true);

            bNew.setVisible(false);
            bEdit.setVisible(false);
            bDelete.setVisible(false);
            bSaveNew.setVisible(false);
        } if(state == ActionState.READ) {
            bSave.setVisible(false);
            bSaveNew.setVisible(false);
            bCancel.setVisible(false);
            
            bNew.setVisible(true);
            bEdit.setVisible(true);
            bDelete.setVisible(true);
            
            cfStatus.addItem("Actived");
            cfStatus.addItem("Inactived");
            
            cfHandler.addItem("No Action");
            cfHandler.addItem("Activate");
            cfHandler.addItem("Deactivate");
            cfHandler.addItem("Delete");
            
            tfName.setEnabled(false);
            tfRemark.setEnabled(false);
            cfHandler.setEnabled(false);
    }

    private void initData() {
        if(object != null) {
            tfName.setText(object.getName());
            tfRemark.setText(object.getRemark());
            cfStatus.setSelectedIndex(object.getStatus() - 1);
            cfStatus.setSelectedIndex(0);
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    private boolean isModelValid() {
        if (lfContact.getName().equals("")) {
            lfContact.setBorder(BorderFactory.createLineBorder(Color.red, 1));
            return false;
        }
        
        return true;
    }

    // </editor-fold>

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton bb = (JButton) e.getSource();
            if(bb == bSave) {
                if(!isModelValid()) {
                   return;
                }
                firePropertyChange(PropertyChangeField.SAVING.toString(), false, true);
                HWaitDialog dialog = new HWaitDialog("Save Data");
                
                object = new ContactGroup();
                object.setName(tfName.getText().trim());
                object.setRemark(tfRemark.getText().trim());
                
                SwingWorker<Boolean, Void> td = new SwingWorker<Boolean, Void>() {
                  @Override
                  protected Boolean doInBackground() throws Exception {
                      boolean saved = false;
                      try {
                          session = HibernateUtil.getSessionFactory().openSession();
                          session.getTransaction().begin();
                          
                          if(state == ActionState.CREATE) {
                              session.save(object);
                              saved = true;
                          }else if (state == ActionState.UPDATE) {
                              session.update(object);
                              saved = true;
                          } else if(state == ActionState.DELETE) {
                              session.delete(object);
                              saved = true;
                          }
                          
                          if(saved == true) { 
                            session.getTransaction().commit();
                            saved = true;
                          } else {
                            session.getTransaction().rollback();
                            saved = false;
                          }
                          
                      } catch (Exception ex) {
                          session.getTransaction().rollback();
                          saved  = false;
                          Logger.getLogger(ContactGroupActionForm.class.getName()).log(Level.SEVERE, null, ex);
                      } finally {
                        session.close();
                      }
                      return saved;
                  }
                }
                td.addPropertyChangeListener(new PropertyChangeListener() {
                
                    /**
                     *
                     * @param evt
                     */
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        if ("state".equals(evt.getPropertyName())
                            && SwingWorker.StateValue.DONE == evt.getNewValue()) {
                            try {
                                if (t1.get() == true) {
                                    dialog.setVisible(false);
                                    dialog.dispose();
                                    JOptionPane.showMessageDialog(null, "Saving new template successfull", "New Contact Group", JOptionPane.INFORMATION_MESSAGE);
                                }
                                firePropertyChange(PropertyChangeField.SAVING.toString(), true, false);
                            } catch (InterruptedException | ExecutionException ex) {
                                Logger.getLogger(MessageTemplateActionForm.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                });
                td.execute();
                dialog.setVisible(true);
            } else if(bb == bCancel) {
                firePropertyChange(PropertyChangeField.SAVING.toString(), true, false);
            }
        }
    }
}
