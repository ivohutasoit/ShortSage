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

public class MessageTemplateActionForm extends HActionForm<MessageTemplate> {

    private JXTextField fName;
    private JTextArea fText;
    private JComboBox cmStatus, cmHandler;

    public MessageTemplateActionForm() {
        super();
    }

    public MessageTemplateActionForm(MessageTemplate template) {
        super(template);
    }

    public MessageTemplateActionForm(ActionState state, MessageTemplate template) {
        super(state, template);
    }

    @Override
    public void initComponents() {
        super.initComponents();
        setPreferredSize(new Dimension(450, 250));
        
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

    @Override
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

    @Override
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
    
    public void save() {
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
