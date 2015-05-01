package com.softhaxi.shortsage.v1.forms;

import com.softhaxi.shortsage.v1.desktop.HNumberedTable;
import com.softhaxi.shortsage.v1.desktop.HWaitDialog;
import com.softhaxi.shortsage.v1.enums.ActionState;
import com.softhaxi.shortsage.v1.dto.ContactGroup;
import com.softhaxi.shortsage.v1.dto.ContactGroupLine;
import com.softhaxi.shortsage.v1.enums.PropertyChangeField;
import com.softhaxi.shortsage.v1.renderer.TableHeaderCenterRender;
import com.softhaxi.shortsage.v1.util.HibernateUtil;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SwingWorker;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import net.java.dev.designgridlayout.DesignGridLayout;
import net.java.dev.designgridlayout.LabelAlignment;
import org.hibernate.Session;
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
    
    private JPanel pForm;

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
    private JButton bNewLine, bDeleteLine, bExportLine, bImportLine, bRefreshLine;

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
        this(object, ActionState.EDIT);
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
        setPreferredSize(new Dimension(450, 250));
        
        pForm = new JPanel(new BorderLayout());
        add(pForm, BorderLayout.CENTER);
        
        
        initToolbar();
        initFormPanel();
        
        if (state != ActionState.CREATE) {
            setPreferredSize(new Dimension(450, 400));
            initLinePanel();
        }
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

        add(pToolbar, BorderLayout.PAGE_START);
    }

    /**
     *
     */
    private void initFormPanel() {
        JPanel pfHeader = new JPanel();

        tfName = new JXTextField(RES_GLOBAL.getString("label.contact.group"));
        tfRemark = new JTextArea();
        tfRemark.setLineWrap(true);
        tfRemark.setRows(3);

        cfStatus = new JComboBox();
        cfStatus.setEnabled(false);
        cfHandler = new JComboBox();

        DesignGridLayout layout = new DesignGridLayout(pfHeader);
        layout.labelAlignment(LabelAlignment.RIGHT);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.contact.group"))).add(tfName).empty();
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.desc"))).add(new JScrollPane(tfRemark));
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.status"))).add(cfStatus).empty(2);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.handler"))).add(cfHandler).empty(2);

        pForm.add(pfHeader, BorderLayout.NORTH);
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
        //tbLine.add(new JToolBar.Separator());

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
        ttLine.getTableHeader().setDefaultRenderer(new TableHeaderCenterRender(ttLine));
        ttLine.getColumnModel().getColumn(0).setPreferredWidth(200);
        ttLine.getColumnModel().getColumn(1).setPreferredWidth(200);

        JScrollPane sPane = new JScrollPane(ttLine);
        HNumberedTable rowTable = new HNumberedTable(ttLine);
        sPane.setRowHeaderView(rowTable);
        sPane.setCorner(JScrollPane.UPPER_LEFT_CORNER,
                rowTable.getTableHeader());

        pTable.add(sPane, BorderLayout.CENTER);

        pForm.add(pTable, BorderLayout.CENTER);
    }

    private void initListeners() {
        bSave.addActionListener(this);
        bCancel.addActionListener(this);
    }

    private void initState() {
        cfStatus.removeAllItems();
        cfHandler.removeAllItems();

        if (state == ActionState.CREATE
                || state == ActionState.EDIT) {

            if (state == ActionState.CREATE) {
                cfStatus.addItem("Create");
                cfHandler.addItem("Create");
                cfHandler.setEnabled(false);
            } else {
                cfStatus.addItem("Active");
                cfStatus.addItem("Inactive");

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
        }
    }

    private void initData() {
        if (object != null) {
            tfName.setText(object.getName());
            tfRemark.setText(object.getRemark());
            cfStatus.setSelectedIndex(object.getStatus());
            cfHandler.setSelectedIndex(0);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    private boolean isModelValid() {
        if (tfName.getText().equals("")) {
            tfName.setBorder(BorderFactory.createLineBorder(Color.red, 1));
            return false;
        }

        return true;
    }

    // </editor-fold>
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton bb = (JButton) e.getSource();
            if (bb == bSave) {
                if (!isModelValid()) {
                    return;
                }
                firePropertyChange(PropertyChangeField.SAVING.toString(), false, true);
                final HWaitDialog dialog = new HWaitDialog("Save Data");

                object = new ContactGroup();
                object.setName(tfName.getText().trim());
                object.setRemark(tfRemark.getText().trim());

                final SwingWorker<Boolean, Void> td = new SwingWorker<Boolean, Void>() {
                    @Override
                    protected Boolean doInBackground() throws Exception {
                        boolean saved = false;
                        try {
                            hSession = HibernateUtil.getSessionFactory().openSession();
                            hSession.getTransaction().begin();

                            if (state == ActionState.CREATE) {
                                hSession.save(object);
                                saved = true;
                            } else if (state == ActionState.EDIT) {
                                hSession.update(object);
                                saved = true;
                            }

                            if (saved == true) {
                                hSession.getTransaction().commit();
                                saved = true;
                            } else {
                                hSession.getTransaction().rollback();
                                saved = false;
                            }

                        } catch (Exception ex) {
                            hSession.getTransaction().rollback();
                            saved = false;
                            Logger.getLogger(ContactGroupActionForm.class.getName()).log(Level.SEVERE, null, ex);
                        } finally {
                            hSession.close();
                        }
                        return saved;
                    }
                };
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
                                if (td.get() == true) {
                                    dialog.setVisible(false);
                                    dialog.dispose();
                                    JOptionPane.showMessageDialog(null, "Saving new template successfull", "New Contact Group", JOptionPane.INFORMATION_MESSAGE);
                                }
                                firePropertyChange(PropertyChangeField.SAVING.toString(), true, false);
                            } catch (InterruptedException | ExecutionException ex) {
                                Logger.getLogger(MessageTemplateActionForm.class.getName()).log(Level.SEVERE, null, ex);
                                firePropertyChange(PropertyChangeField.SAVING.toString(), true, false);
                            }
                        }
                    }
                });
                td.execute();
                dialog.setVisible(true);
            } else if (bb == bCancel) {
                firePropertyChange(PropertyChangeField.SAVING.toString(), true, false);
            }
        }
    }
}
