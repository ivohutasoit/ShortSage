package com.softhaxi.shortsage.v1.forms;

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
