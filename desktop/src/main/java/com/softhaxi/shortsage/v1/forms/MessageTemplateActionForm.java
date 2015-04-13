package com.softhaxi.shortsage.v1.forms;

import com.softhaxi.shortsage.v1.desktop.HActionForm;
import com.softhaxi.shortsage.v1.dto.MessageTemplate;
import com.softhaxi.shortsage.v1.enums.ActionState;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Date;
import java.util.UUID;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import net.java.dev.designgridlayout.DesignGridLayout;
import net.java.dev.designgridlayout.LabelAlignment;
import org.jdesktop.swingx.JXTextField;

public class MessageTemplateActionForm extends JPanel {
    
    private static final ResourceBundle RES_GLOBAL = ResourceBundle.getBundle("global");

    private MessageTemplate object
    private ActionState = state;
    
    /**
     * Tool bar items
     */
    private JButton bNew, bEdit, bDelete;
    private JButton bSave, bSaveNew, bCancel;
    private JButton bTest;

    /**
     * Fields
     */
    private JXTextField fName;
    private JTextArea fText;
    private JComboBox cmStatus, cmHandler;
    
    /**
     * Database connection
     */
    private Session hSession;

    public MessageTemplateActionForm() {
        this(null, ActionState.CREATE);
    }

    public MessageTemplateActionForm(MessageTemplate object) {
        this(null, ActionState.SHOW);
    }

    public MessageTemplateActionForm(MessageTemplate object, ActionState state) {
        this.object = object;
        this.state = state;
        
        initComponents();
        initListeners();
        initState();
    }

    // <editor-fold defaultstate="collapsed" desc="Region Initialization">
    /**
     * 
     */
    public void initComponents() {
        setPreferredSize(new Dimension(450, 250));
        
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

        bTest = new JButton(RES_GLOBAL.getString("label.test.gateway"),
                new ImageIcon(getClass().getClassLoader().getResource("images/ic_modem_connect_16.png")));
        pToolbar.add(bTest);
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
        fName = new JXTextField("Template Name");
        fText = new JTextArea();
        fText.setRows(3);
        cmStatus = new JComboBox();
        cmHandler = new JComboBox();
        
        JPanel pForm = new JPanel();
        DesignGridLayout layout = new DesignGridLayout(pForm);
        layout.labelAlignment(LabelAlignment.RIGHT);
        layout.row().grid(new JLabel("Name :")).add(fName).empty();
        layout.row().grid(new JLabel("Message :")).add(new JScrollPane(fText));
        layout.row().grid(new JLabel("Status :")).add(cmStatus).empty(2);
        layout.row().grid(new JLabel("Handler :")).add(cmHandler).empty(2);
        
        add(pForm, BorderLayout.CENTER);
    }
    
    /**
     * 
     */ 
    private void initListeners() {
        
    }

    /**
     * 
     */
    public void initState() {
        super.initState();
        if(state == ActionState.CREATE) {
            cmStatus.removeAllItems();
            cmStatus.addItem("CREATE");
            cmStatus.setEnabled(false);
            
            cmHandler.removeAllItems();
            cmHandler.addItem("CREATED");
            cmHandler.setEnabled(false);
        } else if(state == ActionState.SHOW || state == ActionState.EDIT) {
            cmStatus.removeAllItems();
            cmStatus.addItem("DRAFT");
            cmStatus.addItem("ACTIVE");
            cmStatus.addItem("INACTIVE");
            
            cmHandler.removeAllItems();
            cmHandler.addItem("NO ACTION");
            cmHandler.addItem("ACTIVATED");
            cmHandler.addItem("DEACTIVATED");
            cmHandler.addItem("DELETED");
            
            if(state == ActionState.SHOW) {
                cmStatus.setEnabled(false);
                cmHandler.setEnabled(false);
            } else {
                cmStatus.setEnabled(false);
                cmStatus.setEnabled(true);
            }
        }
    }

    /**
     * 
     */
    public void initData() {
        if(state == ActionState.SHOW || state == ActionState.EDIT) {
            if(object != null) {
                fName.setText(object.getName());
                fText.setText(object.getText());
                cmStatus.setSelectedIndex(object.getStatus());
                cmHandler.setSelectedIndex(0);
            }
        }
    }
    
    // </editor-fold>
    
    private void save() {
        if(state == ActionState.CREATE) {
            object = new MessageTemplate();
            object.setId(UUID.randomUUID().toString());
            object.setName(fName.getText().trim());
            object.setText(fText.getText().trim());
            object.setStatus(0);
            object.setCreatedBy("SYSTEM");
            object.setCreatedOn(new Date());
            object.setModifiedBy("SYSTEM");
            object.setModifiedOn(new Date());
            object.setDeletedState(0);
        }
    }
}
