package com.softhaxi.shortsage.v1.forms;

import com.softhaxi.shortsage.v1.component.CActionForm;
import com.softhaxi.shortsage.v1.component.CDialog;
import com.softhaxi.shortsage.v1.component.CZebraTable;
import com.softhaxi.shortsage.v1.enums.ActionState;
import com.softhaxi.shortsage.v1.dto.ContactGroup;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
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

public class ContactGroupActionForm extends CActionForm<ContactGroup>
        implements ActionListener {

    private JTextField txId, txName;
    private JTextArea txRemark;
    private JComboBox cmStatus, cmHandler;

    private CZebraTable tbContact;
    private JButton bcNew, bcDelete, bcExport, bcImport, bcRefresh;

    public ContactGroupActionForm() {
        super();
    }

    public ContactGroupActionForm(ContactGroup group) {
        super(group);
    }

    public ContactGroupActionForm(ActionState state, ContactGroup group) {
        super(state, group);
    }

    @Override
    public void initComponents() {
        super.initComponents();

        txId = new JTextField();
        txName = new JTextField();
        txRemark = new JTextArea();
        txRemark.setLineWrap(true);
        txRemark.setRows(3);

        cmStatus = new JComboBox(new String[]{
            "Active",
            "Deactive"
        });
        cmStatus.setEnabled(false);

        cmHandler = new JComboBox(new String[]{
            "No Action",
            "Activated",
            "Deactivated",
            "Deleted"
        });

        if (state == ActionState.CREATE) {
            setPreferredSize(new Dimension(450, 250));
            add(getPanelHeader(), BorderLayout.CENTER);
        } else {
            setPreferredSize(new Dimension(450, 400));
            JPanel pForm = new JPanel(new BorderLayout());

            pForm.add(getPanelHeader(), BorderLayout.NORTH);
            pForm.add(getPanelTable(), BorderLayout.CENTER);
            add(pForm, BorderLayout.CENTER);
        }

        bSave.addActionListener(this);
    }

    private JPanel getPanelHeader() {
        JPanel pHeader = new JPanel();

        DesignGridLayout layout = new DesignGridLayout(pHeader);
        layout.labelAlignment(LabelAlignment.RIGHT);
        layout.row().grid(new JLabel("Group :")).add(txName).empty();
        layout.row().grid(new JLabel("Description :")).add(new JScrollPane(txRemark));
        layout.row().grid(new JLabel("Status :")).add(cmStatus).empty(2);
        layout.row().grid(new JLabel("Handler :")).add(cmHandler).empty(2);

        return pHeader;
    }

    private JPanel getPanelTable() {
        JPanel pTable = new JPanel(new BorderLayout());
        JToolBar toContact = new JToolBar();
        toContact.setFloatable(false);
        toContact.setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(2, 2, 2, 2)));
        
        bcNew = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_plus_12.png")));
        bcNew.addActionListener(this);
        toContact.add(bcNew);
        toContact.add(new JToolBar.Separator());

        bcImport = new JButton("Import", new ImageIcon(getClass().getClassLoader().getResource("images/ic_upload_12.png")));
        toContact.add(bcImport);

        bcExport = new JButton("Export", new ImageIcon(getClass().getClassLoader().getResource("images/ic_download_12.png")));
        toContact.add(bcExport);
        toContact.add(new JToolBar.Separator());

        bcDelete = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_minus_12.png")));
        toContact.add(bcDelete);
        
        toContact.add(Box.createHorizontalGlue());
        bcRefresh = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_refresh_12.png")));
        toContact.add(bcRefresh);

        pTable.add(toContact, BorderLayout.NORTH);

        tbContact = new CZebraTable();
        pTable.add(tbContact, BorderLayout.CENTER);

        return pTable;
    }

    @Override
    public void initState() {
        super.initState();

        if (state == ActionState.CREATE) {
            txId.setEditable(false);
            cmHandler.setEnabled(false);
        } else if (state == ActionState.SHOW) {
            txName.setEditable(false);
            txRemark.setEditable(false);
            cmHandler.setEnabled(true);
            bCancel.setVisible(false);
            bSave.setVisible(false);
            bSaveNew.setVisible(false);
        }
    }

    @Override
    public void initData() {
        if (object != null) {
            txName.setText(object.getName());
            txRemark.setText(object.getRemark());
            cmHandler.setSelectedIndex(0);
            cmStatus.setSelectedIndex(object.getStatus());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton bSource = (JButton) e.getSource();
            if (bSource == bSave || bSource == bSaveNew) {
                if (save()) {
                    if (bSource == bSave) {
                        CDialog dialog = null;

                        if (getRootPane().getParent() instanceof CDialog) {
                            dialog = (CDialog) getRootPane().getParent();
                            dialog.dispose();
                        }
                        final ContactGroupActionForm form = new ContactGroupActionForm(object);
                        dialog = new CDialog(null, form, "Contact Group " + object.getName(), true);
                        try {
                            Toolkit kit = Toolkit.getDefaultToolkit();
                            dialog.setIconImage(kit.createImage(ClassLoader.getSystemResource("images/ic_logo.png")));
                        } catch (Exception ex) {
                            System.err.printf(ex.getMessage());
                        }
                        dialog.setVisible(true);
                    }
                }
            }
        }
    }

    private boolean save() {
        boolean saved = false;

        object = new ContactGroup();
        object.setId(UUID.randomUUID().toString());
        object.setName(txName.getText().trim());
        object.setRemark(txRemark.getText().trim());
        object.setStatus(1);
        object.setCreatedBy("SYSTEM");
        object.setCreatedOn(new Date());
        saved = true;

        return saved;
    }
}
