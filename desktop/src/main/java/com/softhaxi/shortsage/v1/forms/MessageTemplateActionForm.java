package com.softhaxi.shortsage.v1.forms;

import com.softhaxi.shortsage.v1.dto.MessageTemplate;
import com.softhaxi.shortsage.v1.enums.ActionState;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import net.java.dev.designgridlayout.DesignGridLayout;
import net.java.dev.designgridlayout.LabelAlignment;
import org.hibernate.Session;
import org.jdesktop.swingx.JXTextField;

public class MessageTemplateActionForm extends JPanel {

    private static final ResourceBundle RES_GLOBAL = ResourceBundle.getBundle("global");

    private MessageTemplate object;
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
    private JTextArea tfText;
    private JComboBox cfStatus, cfHandler;

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
    private void initComponents() {
        setLayout(new BorderLayout());
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
        tfName = new JXTextField("Template Name");
        tfText = new JTextArea();
        tfText.setRows(3);
        cfStatus = new JComboBox();
        cfHandler = new JComboBox();

        JPanel pForm = new JPanel();
        DesignGridLayout layout = new DesignGridLayout(pForm);
        layout.labelAlignment(LabelAlignment.RIGHT);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.template.name") + " :")).add(tfName).empty();
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.message.text") + " :")).add(new JScrollPane(tfText));
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.status") + " :")).add(cfStatus).empty(2);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.handler") + " :")).add(cfHandler).empty(2);

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
    private void initState() {
        if (state == ActionState.CREATE) {
            cfStatus.removeAllItems();
            cfStatus.addItem("CREATE");
            cfStatus.setEnabled(false);

            cfHandler.removeAllItems();
            cfHandler.addItem("CREATED");
            cfHandler.setEnabled(false);
            
            bNew.setVisible(false);
            bEdit.setVisible(false);
            bDelete.setVisible(false);
            bSaveNew.setVisible(false);
        } else if (state == ActionState.SHOW || state == ActionState.EDIT) {
            cfStatus.removeAllItems();
            cfStatus.addItem("DRAFT");
            cfStatus.addItem("ACTIVE");
            cfStatus.addItem("INACTIVE");

            cfHandler.removeAllItems();
            cfHandler.addItem("NO ACTION");
            cfHandler.addItem("ACTIVATED");
            cfHandler.addItem("DEACTIVATED");
            cfHandler.addItem("DELETED");

            if (state == ActionState.SHOW) {
                cfStatus.setEnabled(false);
                cfHandler.setEnabled(false);
            } else {
                cfStatus.setEnabled(false);
                cfStatus.setEnabled(true);
            }
        }
    }

    /**
     *
     */
    public void initData() {
        if (state == ActionState.SHOW || state == ActionState.EDIT) {
            if (object != null) {
                tfName.setText(object.getName());
                tfText.setText(object.getText());
                cfStatus.setSelectedIndex(object.getStatus());
                cfHandler.setSelectedIndex(0);
            }
        }
    }

    // </editor-fold>
    private void save() {
        if (state == ActionState.CREATE) {
            object = new MessageTemplate();
            object.setId(UUID.randomUUID().toString());
            object.setName(tfName.getText().trim());
            object.setText(tfText.getText().trim());
            object.setStatus(0);
            object.setCreatedBy("SYSTEM");
            object.setCreatedOn(new Date());
            object.setModifiedBy("SYSTEM");
            object.setModifiedOn(new Date());
            object.setDeletedState(0);
        }
    }
}
