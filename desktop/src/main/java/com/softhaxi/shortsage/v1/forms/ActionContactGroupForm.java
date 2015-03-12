package com.softhaxi.shortsage.v1.forms;

import com.softhaxi.shortsage.v1.enums.ActionState;
import com.softhaxi.shortsage.v1.model.ContactGroup;
import javax.swing.JPanel;

public class ActionContactGroupForm extends JPanel {
    private ActionState state;
    private ContactGroup object;
    
    public ActionContactGroupForm() {
        this(ActionState.CREATE, null);
    }
    
    public ActionContactGroupForm(ActionState state, ContactGroup object) {
        this.state = state;
        this.object = object;
        
        initComponents();
        initState();
        initData();
    }
    
    private void initComponents() {
    }
    
    private void initState() {
      
    }
    
    private void initData() {
    }
}
