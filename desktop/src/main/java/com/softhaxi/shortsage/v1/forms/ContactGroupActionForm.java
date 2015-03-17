package com.softhaxi.shortsage.v1.forms;

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
    private void initComponents() {
      super.initCompnents();
    }
    
    @Override
    private void initState() {
      super.initState();
    }
    
    @Override
    private void initData() {
    }
}
