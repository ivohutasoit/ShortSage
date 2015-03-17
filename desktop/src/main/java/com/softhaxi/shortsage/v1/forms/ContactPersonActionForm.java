package com.softhaxi.shortsafge.v1.forms;

import com.softhaxi.shortsage.v1.components.CZebraTable;
import com.softhaxi.shortsage.v1.enums.ActionState;
import com.softhaxi.shortsage.v1.forms.ActionForm;
import com.softhaxi.shortsage.v1.model.ContactPerson;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ContactPersonActionForm extends ActionForm<ContactPerson> {
    private JPanel pHeader;
    private JTextField tID;
    private JTextField tName;
    private JTextField tCompany;
    private JComboBox cStatus;
    private JComboBox cHeadler;
    private JTextArea tRemark;
    
    private JPanel pDetail;
    private CZebraTable zNumber;
    
    public ContactPersonActionForm() {
      super();
    }
    
    public ContactPersonActionForm(ContactPerson person) {
      super(person);
    }
    
    public ContactPersonActionForm(ActionState state, ContactPerson person) {
      super(state, person);
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
