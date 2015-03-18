package com.softhaxi.shortsage.v1.forms;

import com.softhaxi.shortsage.v1.component.CActionForm;
import com.softhaxi.shortsage.v1.enums.ActionState;
import com.softhaxi.shortsage.v1.model.MessageTemplate;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MessageTemplateActionForm extends CActionForm<MessageTemplate> {

    private JTextField tID;
    private JTextField tName;
    private JComboBox cStatus;
    private JComboBox cHandler;
    private JTextArea tText;

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
    }

    @Override
    public void initState() {
        super.initState();
    }

    @Override
    public void initData() {
    }
}
