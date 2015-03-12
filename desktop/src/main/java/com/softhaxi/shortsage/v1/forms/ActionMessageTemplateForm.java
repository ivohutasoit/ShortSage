package com.softhaxi.shortsage.v1.forms;

import com.softhaxi.shortsage.v1.enums.ActionState;
import com.softhaxi.shortsage.v1.model.MessageTemplate;
import javax.swing.JPanel;

public class ActionMessageTemplateForm extends JPanel {
    
    private ActionState state;
    private MessageTemplate object;
    
    public ActionMessageTemplateForm() {
        this(ActionState.CREATE, null);
    }
    
    public ActionMessageTemplateForm(ActionState state, MessageTemplate object) {
        this.state = state;
        this.object = object;
        
        initComponents();
        initState();
        initData();
    }
    
    private void initComponents() { }
    
    private void initState() { }
    
    private void initData() { }
}
