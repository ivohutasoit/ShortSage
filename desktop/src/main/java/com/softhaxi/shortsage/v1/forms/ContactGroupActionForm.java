package com.softhaxi.shortsage.v1.forms;

import com.softhaxi.shortsage.v1.components.CZebraTable;
import com.softhaxi.shortsage.v1.enums.ActionState;
import com.softhaxi.shortsage.v1.model.ContactGroup;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class ContactGroupActionForm extends ActionForm<ContactGroup> {
    private JPanel pHeader;
    private JTextField tNumber;
    private JTextField tName;
    private JComboBox cStatus;
    private JComboBox cHandle;
    private JTextArea tRemark;
    
    private JPanel pDetail;
    private JToolBar mTable;
    private CZebraTable zTable;
    
    public ContactGroupActionForm() {
      super();
    }
    
    public ContactGroupActionForm(ContactGroup group) {
      super(group);
    }
    
    public ContactGroupActionForm(ActionState state, ContactGroup group) {
      super(state, group);
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
