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
     * Contact Person Detail
     */
    private JXTable tbContact;
    private JButton bcNew, bcDelete, bcExport, bcImport, bcRefresh;

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
            initDetailPanel();
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
        layout.row().grid(new JLabel("Group :")).add(tfName).empty();
        layout.row().grid(new JLabel("Description :")).add(new JScrollPane(tfRemark));
        layout.row().grid(new JLabel("Status :")).add(cfStatus).empty(2);
        layout.row().grid(new JLabel("Handler :")).add(cfHandler).empty(2);
        
        add(pForm, BorderLayout.CENTER);
    }
    
    private void initDetailPanel() {
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

        tbContact = new JXTable();
        pTable.add(new JScrollPane(tbContact), BorderLayout.CENTER);
        
        add(pTable, BorderLayout.SOUTH);
    }
    
    private void initListeners() {
        
    }
 
    private void initState() {

    }

    private void initData() {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton bSource = (JButton) e.getSource();
        }
    }
}
