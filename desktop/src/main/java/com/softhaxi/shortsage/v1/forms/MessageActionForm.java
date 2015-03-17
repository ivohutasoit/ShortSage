package com.softhaxi.shortsage.v1.forms;

import com.softhaxi.shortsage.v1.components.CActionForm;
import com.softhaxi.shortsage.v1.dto.Message;
import com.softhaxi.shortsage.v1.enums.ActionState;
import com.toedter.calendar.JCalendar;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MessageActionForm extends CActionForm<Message> {
    
    private JTextField tContact;
    private JCalendar cDate;
    private JTextArea tText;
    private JComboBox cStatus;
    private JComboBox cHandle;
    
    public MessageActionForm() {
        super();
    }
    
    public MessageActionForm(Message object) {
        super(object);
    }
    
    public MessageActionForm(ActionState state, Message message) {
        super(state, message);
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
