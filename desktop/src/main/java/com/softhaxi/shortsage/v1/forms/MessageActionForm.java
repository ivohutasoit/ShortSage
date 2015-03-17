package com.softhaxi.shortsage.v1.forms;

import com.softhaxi.shortsage.v1.dto.Message;

public class MessageActionForm extends ActionForm<Message> {
    
    private JTextField tContact;
    private JCalendarComboBox cDate;
    private JTextArea tText;
    private JComboBox cStatus;
    private JComboBox cHandle;
    
    public MessageActionForm() {
        super();
    }
    
    public MessageActionForm(Message object) {
        super(object);
    }
    
    public MessageActionForm(ActionState state, Message object) {
        super(state, object)
    }
    
    @Override
    private void initComponents() {
        
    }
    
    @Override
    private void initState() {
        
    }
    
    @Override
    private void initData() {
        if(object != null) {
            
        }
    }
  
}
